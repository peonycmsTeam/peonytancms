package com.peony.peonyfront.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.peony.core.common.utils.UUIDGenerator;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.regionindex.model.Regionindex;
import com.peony.peonyfront.regionindex.service.RegionindexService;

public class ThreadJobRegionIndexTask implements Runnable {

    @Autowired
    private RegionService      regionService;

    @Autowired
    private RegionindexService regionIndexService;

    @Autowired
    private EventnewsService   eventnewsService;

    @Override
    public void run() {

        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(calender.getTime());
        int monthTime = calender.get(Calendar.MONTH) + 1;
        int yearTime = calender.get(calender.YEAR);

        // 查询日期为昨天的regionindex
        List<Regionindex> regionIndexList = this.regionIndexService.selectRegionIndexByDate(time);
        if (regionIndexList.size() > 0) {
            return;
        } else {
            Region region = new Region();
            Regionindex regionIndex = new Regionindex();
            CommonIndex commonIndex = new CommonIndex();
            DecimalFormat df2 = new DecimalFormat("###.00");

            // 获取properties文件中的综合指数计算公式中所乘的参数
            Properties pro = commonIndex.getProperties();
            float heatIndexParam = Float.parseFloat(pro.getProperty("heatIndexParam"));
            float sensitiveIndexParam = Float.parseFloat(pro.getProperty("sensitiveIndexParam"));
            float focusIndexParam = Float.parseFloat(pro.getProperty("focusIndexParam"));

            // 查询区域是省的区域集合
            List<Region> regionList = this.regionService.selectRegion();
            // 遍历regionlist，统计每个省的指数，并插入到数据库中
            for (int i = 0; i < regionList.size(); i++) {
                region = regionList.get(i);
                float heatInde = commonIndex.heatIndexByProvinceId(region.getProvinceid(), eventnewsService);
                float sensitiveIndex = commonIndex.sensitiveIndexByProvinceId(region.getProvinceid(), eventnewsService);
                float focusIndex = commonIndex.focusIndexByProvinceId(region.getProvinceid(), eventnewsService);
                // float compositeIndex =
                // commonIndex.compositeIndexByProvinceId(region.getProvinceid(),eventnewsService);
                // 综合指数=热度指数*30%+敏感指数*40%+关注指数*30%
                float count5 = (float) (heatInde * heatIndexParam + sensitiveIndex * sensitiveIndexParam + focusIndex * focusIndexParam);
                float compositeIndex = Float.parseFloat(df2.format(count5));

                regionIndex.setId(UUIDGenerator.random());
                regionIndex.setRegionid(regionList.get(i).getProvinceid());
                regionIndex.setRegionname(regionList.get(i).getRegionname());
                regionIndex.setDate(time);
                regionIndex.setMonth(String.valueOf(monthTime));
                regionIndex.setYear(String.valueOf(yearTime));
                regionIndex.setHeatindex(heatInde);
                regionIndex.setSensitiveindex(sensitiveIndex);
                regionIndex.setFocusindex(focusIndex);
                regionIndex.setCompositeindex(compositeIndex);
                regionIndex.setType("2");

                // 统计的指数插入到数据库
                this.regionIndexService.insert(regionIndex);

                Map map = new HashMap();
                int provinceId = regionList.get(i).getProvinceid();
                map.put("provinceId", provinceId);

                // 根据省的parentid查询省的所有市的regionid集合
                List<Region> regionIdList = regionService.selectRegionByProvinceId(map);
                for (int j = 0; j < regionIdList.size(); j++) {

                    int regionId = regionIdList.get(j).getRegionid();
                    String regionName = regionIdList.get(j).getRegionname();

                    float heatIndeByRegionId = commonIndex.heatIndexByRegionId(regionList.get(i).getProvinceid(), regionId, eventnewsService);
                    float sensitiveIndexByRegionId = commonIndex.sensitiveIndexByRegionId(regionList.get(i).getProvinceid(), regionId, eventnewsService);
                    float focusIndexByRegionId = commonIndex.focusIndexByRegion(regionList.get(i).getProvinceid(), regionId, eventnewsService);
                    // float compositeIndexByRegionId =
                    // commonIndex.compositeIndexByRegionId(regionList.get(i).getProvinceid(),
                    // regionId, eventnewsService);
                    // 综合指数=热度指数*30%+敏感指数*40%+关注指数*30%
                    float count6 = (float) (heatIndeByRegionId * heatIndexParam + sensitiveIndexByRegionId * sensitiveIndexParam + focusIndexByRegionId * focusIndexParam);
                    float compositeIndexByRegionId = Float.parseFloat(df2.format(count6));

                    regionIndex.setId(UUIDGenerator.random());
                    regionIndex.setRegionid(regionId);
                    regionIndex.setRegionname(regionName);
                    regionIndex.setDate(time);
                    regionIndex.setMonth(String.valueOf(monthTime));
                    regionIndex.setYear(String.valueOf(yearTime));
                    regionIndex.setHeatindex(heatIndeByRegionId);
                    regionIndex.setSensitiveindex(sensitiveIndexByRegionId);
                    regionIndex.setFocusindex(focusIndexByRegionId);
                    regionIndex.setCompositeindex(compositeIndexByRegionId);
                    regionIndex.setType("1");

                    // 统计的指数插入到数据库
                    this.regionIndexService.insert(regionIndex);
                }
            }
            System.out.println("地域执行完成");
        }
    }
}
