package com.peony.peonyfront.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.peony.core.common.utils.UUIDGenerator;
import com.peony.peonyfront.event.model.Event;

import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.eventindex.model.EventIndex;
import com.peony.peonyfront.mediacount.model.MediaCount;
import com.peony.peonyfront.mediacount.service.MediaCountService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.regionindex.model.Regionindex;

public class ThreadJobMediaCountTask implements Runnable {

    @Autowired
    private RegionService     regionService;

    @Autowired
    private MediaCountService mediaCountService;

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
        List<MediaCount> mediaCountByTimeList = this.mediaCountService.selectMediaCountByDate(time);
        if (mediaCountByTimeList.size() > 0) {
            return;
        } else {
            Region region = new Region();
            Event event = new Event();
            Regionindex regionIndex = new Regionindex();
            EventIndex eventIndex = new EventIndex();
            MediaCount mediaCount = new MediaCount();
            CommonIndex commonIndex = new CommonIndex();

            // 查询区域是省的区域集合
            List<Region> regionList = this.regionService.selectRegion();

            try {
                // 遍历regionlist，统计各类型下的媒体数量，并插入到数据库中
                for (int i = 0; i < regionList.size(); i++) {
                    region = regionList.get(i);

                    Map map = new HashMap();
                    map.put("beginTime", time);
                    map.put("endTime", time);
                    map.put("provinceId", region.getProvinceid());

                    // 统计各类型下媒体的具体数量
                    List<Map> mediaCountList = this.eventnewsService.CountByMediaType(map);
                    for (int j = 0; j < mediaCountList.size(); j++) {
                        Map remap = mediaCountList.get(j);
                        int count = new Long((Long) remap.get("count")).intValue();
                        String webSite = (String) remap.get("webSite");
                        String type = String.valueOf(remap.get("type"));

                        mediaCount.setId(UUIDGenerator.random());
                        mediaCount.setRegionid(regionList.get(i).getProvinceid());
                        mediaCount.setRegionname(regionList.get(i).getRegionname());
                        mediaCount.setDate(time);
                        mediaCount.setMonth(String.valueOf(monthTime));
                        mediaCount.setYear(String.valueOf(yearTime));
                        mediaCount.setCountmumber(count);
                        mediaCount.setWebsite(webSite);
                        mediaCount.setType(type);

                        // 统计的mediacount插入到数据库
                        this.mediaCountService.insert(mediaCount);
                    }
                }
                System.out.println("媒体执行完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
