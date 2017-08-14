package com.peony.peonyfront.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.peony.core.common.utils.UUIDGenerator;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.eventindex.model.EventIndex;
import com.peony.peonyfront.eventindex.service.EventIndexService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.regionindex.model.Regionindex;

public class ThreadJobEventIndexTask implements Runnable {

    @Autowired
    private RegionService     regionService;

    @Autowired
    private EventIndexService eventIndexService;

    @Autowired
    private EventnewsService  eventnewsService;

    @Override
    public void run() {

        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(calender.getTime());
        int monthTime = calender.get(Calendar.MONTH) + 1;
        int yearTime = calender.get(calender.YEAR);

        // 查询日期为昨天的EventIndex
        List<EventIndex> eventIndexList = this.eventIndexService.selectEventIndexByDate(time);
        if (eventIndexList.size() > 0) {
            return;
        } else {
            Region region = new Region();
            Event event = new Event();
            Regionindex regionIndex = new Regionindex();
            EventIndex eventIndex = new EventIndex();
            CommonIndex commonIndex = new CommonIndex();

            // 获取properties文件中的事件舆情指数计算公式中所乘的参数
            Properties pro = commonIndex.getProperties();
            float mediaAttentionIndexParam = Float.parseFloat(pro.getProperty("mediaAttentionIndexParam"));
            float netizensAttentionIndexParam = Float.parseFloat(pro.getProperty("netizensAttentionIndexParam"));

            try {
                // 查询区域是省的区域集合
                List<Region> regionList = this.regionService.selectRegion();

                // 遍历regionlist，统计每个省的指数，并插入到数据库中
                for (int i = 0; i < regionList.size(); i++) {
                    region = regionList.get(i);

                    int provinceId = region.getProvinceid();
                    Map map = new HashMap();
                    map.put("beginTime", time);
                    map.put("endTime", time);
                    map.put("provinceId", provinceId);

                    // 查询groupcount前十的eventnews
                    List<Eventnews> eventNewsByGroupCoutList = eventnewsService.selectEventnewsByGroupCount(map);
                    // 遍历排行前十的事件
                    for (int k = 0; k < eventNewsByGroupCoutList.size(); k++) {
                        float mediaAttentionIndex = commonIndex.mediaAttentionIndex(eventNewsByGroupCoutList.get(k).getProvinceid(), eventNewsByGroupCoutList.get(k).getGroupseedid(), eventnewsService);
                        float netizensAttentionIndex = commonIndex.netizensAttentionIndex(eventNewsByGroupCoutList.get(k).getProvinceid(), eventNewsByGroupCoutList.get(k).getGroupseedid(), eventnewsService);
                        // float compositeIndex =
                        // commonIndex.compositeIndexByEvent(eventNewsByGroupCoutList.get(k).getProvinceid(),
                        // eventNewsByGroupCoutList.get(k).getGroupseedid(),
                        // eventnewsService);
                        DecimalFormat df2 = new DecimalFormat("###.00");
                        float count5 = (float) (mediaAttentionIndex * mediaAttentionIndexParam + netizensAttentionIndex * netizensAttentionIndexParam);
                        float compositeIndex = Float.parseFloat(df2.format(count5));

                        eventIndex.setId(UUIDGenerator.random());
                        eventIndex.setMediaattentionindex(mediaAttentionIndex);
                        eventIndex.setNetizensattentionindex(netizensAttentionIndex);
                        eventIndex.setCompositeindex(compositeIndex);
                        eventIndex.setTitle(eventNewsByGroupCoutList.get(k).getTitle());
                        eventIndex.setRegionid(regionList.get(i).getProvinceid());
                        eventIndex.setRegionname(regionList.get(i).getRegionname());
                        eventIndex.setDate(time);
                        eventIndex.setMonth(String.valueOf(monthTime));
                        eventIndex.setYear(String.valueOf(yearTime));
                        eventIndex.setType("2");

                        // 统计的指数插入到数据库
                        this.eventIndexService.insert(eventIndex);
                    }

                    // 根据省的parentid查询省的所有市的regionid集合
                    List<Region> regionIdList = regionService.selectRegionByProvinceId(map);
                    // 遍历市集合
                    for (int j = 0; j < regionIdList.size(); j++) {

                        map.put("regionId", regionIdList.get(j).getRegionid());
                        // 查询市区域groupcount前十的eventnews
                        List<Eventnews> eventNewsByRegionAndGroupCoutList = eventnewsService.selectEventnewsByRegionAndGroupCount(map);
                        // 遍历市区域排行前十的事件
                        for (int k = 0; k < eventNewsByRegionAndGroupCoutList.size(); k++) {

                            // float mediaAttentionIndex =
                            // commonIndex.mediaAttentionIndexByRegionId(provinceId,regionIdList.get(j).getRegionid(),
                            // eventNewsByRegionAndGroupCoutList.get(k).getGroupseedid(),
                            // eventnewsService);
                            // float netizensAttentionIndex =
                            // commonIndex.netizensAttentionIndexByRegionId(provinceId,regionIdList.get(j).getRegionid(),
                            // eventNewsByRegionAndGroupCoutList.get(k).getGroupseedid(),
                            // eventnewsService);
                            // float compositeIndex =
                            // commonIndex.compositeIndexByEventAndRegionId(provinceId,regionIdList.get(j).getRegionid(),
                            // eventNewsByRegionAndGroupCoutList.get(k).getGroupseedid(),
                            // eventnewsService);

                            Map mapIndex = commonIndex.netizensAttentionIndexByRegionId(provinceId, regionIdList.get(j).getRegionid(), eventNewsByRegionAndGroupCoutList.get(k).getGroupseedid(), eventnewsService);
                            float mediaAttentionIndex = (Float) mapIndex.get("countMediaIndex");
                            float netizensAttentionIndex = (Float) mapIndex.get("count9");

                            DecimalFormat df2 = new DecimalFormat("###.00");
                            float count5 = (float) (mediaAttentionIndex * mediaAttentionIndexParam + netizensAttentionIndex * netizensAttentionIndexParam);
                            float compositeIndex = Float.parseFloat(df2.format(count5));

                            eventIndex.setId(UUIDGenerator.random());
                            eventIndex.setMediaattentionindex(mediaAttentionIndex);
                            eventIndex.setNetizensattentionindex(netizensAttentionIndex);
                            eventIndex.setCompositeindex(compositeIndex);
                            eventIndex.setTitle(eventNewsByRegionAndGroupCoutList.get(k).getTitle());
                            eventIndex.setRegionid(regionIdList.get(j).getRegionid());
                            eventIndex.setRegionname(regionIdList.get(j).getRegionname());
                            eventIndex.setDate(time);
                            eventIndex.setMonth(String.valueOf(monthTime));
                            eventIndex.setYear(String.valueOf(yearTime));
                            eventIndex.setType("1");

                            // 统计的指数插入到数据库
                            this.eventIndexService.insert(eventIndex);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("事件执行完成");
        }
    }
}
