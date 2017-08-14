package com.peony.peonyfront.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.peony.core.common.utils.UUIDGenerator;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.service.EventService;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.industryindex.model.IndustryIndex;
import com.peony.peonyfront.industryindex.service.IndustryIndexService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.regionindex.model.Regionindex;

public class ThreadJobIndustryIndexTask implements Runnable {

    @Autowired
    private RegionService        regionService;

    @Autowired
    private IndustryIndexService industryIndexService;

    @Autowired
    private EventnewsService     eventnewsService;

    @Autowired
    private EventService         eventService;

    @Override
    public void run() {

        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(calender.getTime());
        int monthTime = calender.get(Calendar.MONTH) + 1;
        int yearTime = calender.get(calender.YEAR);

        // 查询日期为昨天的IndustryIndex
        List<IndustryIndex> industryIndexList = this.industryIndexService.selectIndustryIndexByDate(time);
        if (industryIndexList.size() > 0) {
            return;
        } else {
            Region region = new Region();
            Event event = new Event();
            Regionindex regionIndex = new Regionindex();
            IndustryIndex industryIndex = new IndustryIndex();
            CommonIndex commonIndex = new CommonIndex();
            DecimalFormat df2 = new DecimalFormat("###.00");

            // 获取properties文件中的综合指数计算公式中所乘的参数
            Properties pro = commonIndex.getProperties();
            float heatIndexParam = Float.parseFloat(pro.getProperty("heatIndexParam"));
            float sensitiveIndexParam = Float.parseFloat(pro.getProperty("sensitiveIndexParam"));
            float focusIndexParam = Float.parseFloat(pro.getProperty("focusIndexParam"));

            try {
                // 查询区域是省的区域集合
                List<Region> regionList = this.regionService.selectRegion();
                // 查询行业集合
                List<Event> eventList = this.eventService.findEvent();
                // 遍历regionlist，统计每个省的指数，并插入到数据库中
                for (int i = 0; i < regionList.size(); i++) {
                    region = regionList.get(i);
                    // 遍历 eventList统计各个行业的指数，并插入到数据库中
                    for (int j = 0; j < eventList.size(); j++) {
                        event = eventList.get(j);
                        float heatInde = commonIndex.heatIndexByProvinceIdAndIndustry(region.getProvinceid(), eventList.get(j).getEventid(), eventnewsService);
                        float sensitiveIndex = commonIndex.sensitiveIndexByProvinceIdAndIndustry(region.getProvinceid(), eventList.get(j).getEventid(), eventnewsService);
                        float focusIndex = commonIndex.focusIndexByIndustry(region.getProvinceid(), eventList.get(j).getEventid(), eventnewsService);
                        // float compositeIndex =
                        // commonIndex.compositeIndexByIndustry(region.getProvinceid(),
                        // eventList.get(j).getEventid(), eventnewsService);
                        // 综合指数=热度指数*30%+敏感指数*40%+关注指数*30%
                        float count5 = (float) (heatInde * heatIndexParam + sensitiveIndex * sensitiveIndexParam + focusIndex * focusIndexParam);
                        float compositeIndex = Float.parseFloat(df2.format(count5));

                        industryIndex.setId(UUIDGenerator.random());
                        industryIndex.setRegionid(regionList.get(i).getProvinceid());
                        industryIndex.setRegionname(regionList.get(i).getRegionname());
                        industryIndex.setEventid(eventList.get(j).getEventid());
                        industryIndex.setEventname(eventList.get(j).getEventname());
                        industryIndex.setDate(time);
                        industryIndex.setMonth(String.valueOf(monthTime));
                        industryIndex.setYear(String.valueOf(yearTime));
                        industryIndex.setHeatindex(heatInde);
                        industryIndex.setSensitiveindex(sensitiveIndex);
                        industryIndex.setFocusindex(focusIndex);
                        industryIndex.setCompositeindex(compositeIndex);
                        industryIndex.setType("2");

                        // 统计的指数插入到数据库
                        this.industryIndexService.insert(industryIndex);
                    }

                    Map map = new HashMap();
                    int provinceId = regionList.get(i).getProvinceid();
                    map.put("provinceId", provinceId);
                    // 根据省的parentid查询省的所有市的regionid集合
                    List<Region> regionIdList = regionService.selectRegionByProvinceId(map);
                    // 遍历市集合
                    for (int j = 0; j < regionIdList.size(); j++) {

                        Map map2 = new HashMap();
                        map2.put("provinceId", provinceId);
                        map2.put("regionId", regionIdList.get(j).getRegionid());
                        map2.put("beginTime", time);
                        map2.put("endTime", time);

                        // 查询市所有的行业的信息重复量
                        List<Map> eventNewsList = eventnewsService.CountByGroupCountAndRegionIdAndIndustry(map2);
                        Map<Integer, List> map1 = new HashMap();

                        for (int h = 0; h < eventNewsList.size(); h++) {

                            Map remap = eventNewsList.get(h);
                            int id = (Integer) remap.get("eventId");

                            List<Map> neweventlist = new ArrayList();

                            if (map1.get(id) != null) {
                                map1.get(id).add(remap);
                            } else {
                                neweventlist.add(remap);
                                map1.put(id, neweventlist);
                            }
                        }

                        // 统计市级行业的去重前数据
                        List<Map> evnewNewsProvinceIdList = eventnewsService.CountByRegionIdAndIndustry(map2);
                        // 统计市级行业的自媒体数据量
                        List<Map> evnewNewsProvinceIdMediaList = eventnewsService.CountByRegionIdAndIndustryMedia(map2);

                        String eventName = "";
                        // 遍历Map1插入市级单位各行业的指数到数据库
                        for (Integer key : map1.keySet()) {

                            List<Map> listMap = map1.get(key);
                            int sensitiveIndexList = 0;
                            int focusIndexList = 0;

                            for (int k = 0; k < evnewNewsProvinceIdList.size(); k++) {
                                int eventId = (Integer) evnewNewsProvinceIdList.get(k).get("eventId");
                                if (eventId == key) {
                                    sensitiveIndexList = new Long((Long) evnewNewsProvinceIdList.get(k).get("count")).intValue();
                                    break;
                                }
                            }
                            for (int k = 0; k < evnewNewsProvinceIdMediaList.size(); k++) {
                                int eventId = (Integer) evnewNewsProvinceIdMediaList.get(k).get("eventId");
                                if (eventId == key) {
                                    focusIndexList = new Long((Long) evnewNewsProvinceIdMediaList.get(k).get("count")).intValue();
                                    break;
                                }
                            }

                            float heatIndexByRegionId = commonIndex.heatIndexByRegionIdAndIndustry(listMap);
                            float sensitiveIndexByRegionId = commonIndex.sensitiveIndexByRegionIdAndIndustry(sensitiveIndexList);
                            float focusIndexByRegionId = commonIndex.focusIndexByRegionIdAndIndustry(focusIndexList);
                            // float compositeIndexByRegionId =
                            // commonIndex.compositeIndexByRegionIdAndIndustry(region.getProvinceid(),
                            // regionIdList.get(j).getRegionid(),
                            // eventList.get(k).getEventid(), eventnewsService);
                            // 综合指数=热度指数*30%+敏感指数*40%+关注指数*30%
                            float count6 = (float) (heatIndexByRegionId * heatIndexParam + sensitiveIndexByRegionId * sensitiveIndexParam + focusIndexByRegionId * focusIndexParam);
                            float compositeIndexByRegionId = Float.parseFloat(df2.format(count6));

                            // 获取行业名字
                            for (int k = 0; k < eventList.size(); k++) {
                                if (eventList.get(k).getEventid() == key) {
                                    eventName = eventList.get(k).getEventname();
                                    break;
                                }
                            }

                            industryIndex.setId(UUIDGenerator.random());
                            industryIndex.setRegionid(regionIdList.get(j).getRegionid());
                            industryIndex.setRegionname(regionIdList.get(j).getRegionname());
                            industryIndex.setEventid(key);
                            industryIndex.setEventname(eventName);
                            industryIndex.setDate(time);
                            industryIndex.setMonth(String.valueOf(monthTime));
                            industryIndex.setYear(String.valueOf(yearTime));
                            industryIndex.setHeatindex(heatIndexByRegionId);
                            industryIndex.setSensitiveindex(sensitiveIndexByRegionId);
                            industryIndex.setFocusindex(focusIndexByRegionId);
                            industryIndex.setCompositeindex(compositeIndexByRegionId);
                            industryIndex.setType("1");

                            // 统计的指数插入到数据库
                            this.industryIndexService.insert(industryIndex);
                        }

                        // 此次遍历是对没有数据的行业插入一条指数为零的数据
                        // 遍历所有行业
                        for (int k = 0; k < eventList.size(); k++) {

                            if (map1.keySet().contains(eventList.get(k).getEventid())) {
                                continue;
                            } else {// 没有数据的行业各指数插入0

                                industryIndex.setId(UUIDGenerator.random());
                                industryIndex.setRegionid(regionIdList.get(j).getRegionid());
                                industryIndex.setRegionname(regionIdList.get(j).getRegionname());
                                industryIndex.setEventid(eventList.get(k).getEventid());
                                industryIndex.setEventname(eventList.get(k).getEventname());
                                industryIndex.setDate(time);
                                industryIndex.setMonth(String.valueOf(monthTime));
                                industryIndex.setYear(String.valueOf(yearTime));
                                industryIndex.setHeatindex((float) 0);
                                industryIndex.setSensitiveindex((float) 0);
                                industryIndex.setFocusindex((float) 0);
                                industryIndex.setCompositeindex((float) 0);
                                industryIndex.setType("1");

                                // 统计的指数插入到数据库
                                this.industryIndexService.insert(industryIndex);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("行业执行完成");
        }
    }
}
