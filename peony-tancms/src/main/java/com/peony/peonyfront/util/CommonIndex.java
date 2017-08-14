package com.peony.peonyfront.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.EventnewsService;

public class CommonIndex implements ApplicationContextAware {

    @Autowired
    private ApplicationContext ctx;

    /**
     * 计算省域热度指数
     * 
     * @param provinceId
     * @return
     */
    public float heatIndexByProvinceId(int provinceId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);

        List<Map> eventNewsList = eventnewsService.CountByGroupCount(map);
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;

        // 统计信息重复量区间的具体信息量个数（和）
        for (int i = 0; i < eventNewsList.size(); i++) {
            Map remap = eventNewsList.get(i);
            int groupCount = (Integer) remap.get("groupCount");
            int count = new Long((Long) remap.get("count")).intValue();

            if (groupCount >= 3000) {
                sum1 += count;
            } else if (groupCount >= 1000) {
                sum2 += count;
            } else if (groupCount >= 500) {
                sum3 += count;
            } else if (groupCount >= 100) {
                sum4 += count;
            } else if (groupCount >= 1) {
                sum5 += count;
            }
        }

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provinceHeatValueSection1Value = Integer.parseInt(pro.getProperty("provinceHeatValueSection1Value"));
        int provinceHeatValueSection2Value = Integer.parseInt(pro.getProperty("provinceHeatValueSection2Value"));
        int provinceHeatValueSection3Value = Integer.parseInt(pro.getProperty("provinceHeatValueSection3Value"));
        int provinceHeatValueSection4Value = Integer.parseInt(pro.getProperty("provinceHeatValueSection4Value"));
        int provinceHeatValueSection5Value = Integer.parseInt(pro.getProperty("provinceHeatValueSection5Value"));

        int provinceHeatIndexSection1Value = Integer.parseInt(pro.getProperty("provinceHeatIndexSection1Value"));
        int provinceHeatIndexSection2Value = Integer.parseInt(pro.getProperty("provinceHeatIndexSection2Value"));
        int provinceHeatIndexSection3Value = Integer.parseInt(pro.getProperty("provinceHeatIndexSection3Value"));
        int provinceHeatIndexSection4Value = Integer.parseInt(pro.getProperty("provinceHeatIndexSection4Value"));

        // 计算省域热度值 信息量个数区间在1-99之间
        // 赋分值是0,100-499之间赋分值是20，500-999之间是100,1000-2999之间是500,3000以上是3000
        int count1 = 0;
        count1 = sum1 * provinceHeatValueSection5Value + provinceHeatValueSection4Value * sum2 + provinceHeatValueSection3Value * sum3 + provinceHeatValueSection2Value * sum4 + provinceHeatValueSection1Value * sum5;

        // 计算省域热度指数 热度指数=区间值n/区间差n*（省域热度值-热度值n）+赋值(n-1)
        float count2 = 0;
        if (count1 >= 20000 && count1 <= 50000) {
            count2 = (float) 10 / 30000 * (count1 - 20000) + provinceHeatIndexSection4Value;
        }
        if (count1 >= 10000 && count1 <= 19999) {
            count2 = (float) 15 / 9999 * (count1 - 10000) + provinceHeatIndexSection3Value;
        }
        if (count1 >= 5000 && count1 <= 9999) {
            count2 = (float) 15 / 4999 * (count1 - 5000) + provinceHeatIndexSection2Value;
        }
        if (count1 >= 1000 && count1 <= 4999) {
            count2 = (float) 20 / 3999 * (count1 - 1000) + provinceHeatIndexSection1Value;
        }
        if (count1 >= 0 && count1 <= 999) {
            count2 = (float) 40 / 999 * (count1 - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count2));
    }

    /**
     * 计算省域敏感指数
     * 
     * @param provinceId
     * @return
     */
    public float sensitiveIndexByProvinceId(int provinceId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);

        // 统计省份的去重前数据
        List<Map> evnewNewsProvinceIdList = eventnewsService.CountByProvinceId(map);

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provincesensitiveIndexSection1Value = Integer.parseInt(pro.getProperty("provincesensitiveIndexSection1Value"));
        int provincesensitiveIndexSection2Value = Integer.parseInt(pro.getProperty("provincesensitiveIndexSection2Value"));
        int provincesensitiveIndexSection3Value = Integer.parseInt(pro.getProperty("provincesensitiveIndexSection3Value"));
        int provincesensitiveIndexSection4Value = Integer.parseInt(pro.getProperty("provincesensitiveIndexSection4Value"));
        int provincesensitiveIndexSection5Value = Integer.parseInt(pro.getProperty("provincesensitiveIndexSection5Value"));
        int provincesensitiveIndexSection6Value = Integer.parseInt(pro.getProperty("provincesensitiveIndexSection6Value"));

        // 计算日度敏感指数值 敏感指数=区间值n/区间差n*（去重前数量-热度值n）+赋值(n-1)
        float count3 = 0;
        for (int i = 0; i < evnewNewsProvinceIdList.size(); i++) {
            Map remap = evnewNewsProvinceIdList.get(i);
            int count = new Long((Long) remap.get("count")).intValue();
            if (count >= 100000) {
                count3 = (float) 5 / 50000 * (count - 100000) + provincesensitiveIndexSection6Value;
            } else if (count >= 50000) {
                count3 = (float) 5 / 49999 * (count - 50000) + provincesensitiveIndexSection5Value;
            } else if (count >= 20000) {
                count3 = (float) 10 / 25000 * (count - 25000) + provincesensitiveIndexSection4Value;
            } else if (count >= 15000) {
                count3 = (float) 10 / 9999 * (count - 15000) + provincesensitiveIndexSection3Value;
            } else if (count >= 10000) {
                count3 = (float) 10 / 4999 * (count - 10000) + provincesensitiveIndexSection2Value;
            } else if (count >= 5000) {
                count3 = (float) 10 / 4999 * (count - 5000) + provincesensitiveIndexSection1Value;
            } else if (count >= 1) {
                count3 = (float) 50 / 4999 * (count - 0);
            }
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count3));
    }

    /**
     * 计算省域关注指数
     * 
     * @param provinceId
     * @return
     */
    public float focusIndexByProvinceId(int provinceId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);

        // 统计省份的自媒体数据量
        List<Map> evnewNewsProvinceIdMediaList = eventnewsService.CountByProvinceIdMedia(map);

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provincefocusIndexSection1Value = Integer.parseInt(pro.getProperty("provincefocusIndexSection1Value"));
        int provincefocusIndexSection2Value = Integer.parseInt(pro.getProperty("provincefocusIndexSection2Value"));
        int provincefocusIndexSection3Value = Integer.parseInt(pro.getProperty("provincefocusIndexSection3Value"));
        int provincefocusIndexSection4Value = Integer.parseInt(pro.getProperty("provincefocusIndexSection4Value"));

        // 计算日度关注指数值 关注指数=区间值n/区间差n*（自媒体数据量-热度值n）+赋值(n-1)
        float count4 = 0;
        for (int i = 0; i < evnewNewsProvinceIdMediaList.size(); i++) {
            Map remap = evnewNewsProvinceIdMediaList.get(i);
            int count = new Long((Long) remap.get("count")).intValue();

            if (count >= 25000) {
                count4 = (float) 5 / 25000 * (count - 25000) + provincefocusIndexSection4Value;
            } else if (count >= 15000) {
                count4 = (float) 20 / 9999 * (count - 15000) + provincefocusIndexSection3Value;
            } else if (count >= 10000) {
                count4 = (float) 15 / 4999 * (count - 10000) + provincefocusIndexSection2Value;
            } else if (count >= 5000) {
                count4 = (float) 30 / 4999 * (count - 5000) + provincefocusIndexSection1Value;
            } else if (count >= 1) {
                count4 = (float) 30 / 4999 * (count - 0);
            }
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count4));
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        ctx = arg0;
    }

    /**
     * 计算行业热度指数
     * 
     * @param provinceId
     * @return
     */
    public float heatIndexByProvinceIdAndIndustry(int provinceId, int eventId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("eventId", eventId);

        List<Map> eventNewsList = eventnewsService.CountByGroupCountAndIndustry(map);
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;

        // 统计行业信息重复量区间的具体信息量个数（和）
        for (int i = 0; i < eventNewsList.size(); i++) {
            Map remap = eventNewsList.get(i);
            int groupCount = (Integer) remap.get("groupCount");
            int count = new Long((Long) remap.get("count")).intValue();

            if (groupCount >= 3000) {
                sum1 += count;
            } else if (groupCount >= 1000) {
                sum2 += count;
            } else if (groupCount >= 500) {
                sum3 += count;
            } else if (groupCount >= 100) {
                sum4 += count;
            } else if (groupCount >= 1) {
                sum5 += count;
            }
        }

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provinceIndustryHeatValueSection1Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatValueSection1Value"));
        int provinceIndustryHeatValueSection2Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatValueSection2Value"));
        int provinceIndustryHeatValueSection3Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatValueSection3Value"));
        int provinceIndustryHeatValueSection4Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatValueSection4Value"));
        int provinceIndustryHeatValueSection5Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatValueSection5Value"));

        int provinceIndustryHeatIndexSection1Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatIndexSection1Value"));
        int provinceIndustryHeatIndexSection2Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatIndexSection2Value"));
        int provinceIndustryHeatIndexSection3Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatIndexSection3Value"));
        int provinceIndustryHeatIndexSection4Value = Integer.parseInt(pro.getProperty("provinceIndustryHeatIndexSection4Value"));

        // 计算行业热度值 信息量个数区间在1-99之间
        // 赋分值是0,100-499之间赋分值是20，500-999之间是100,1000-2999之间是500,3000以上是3000
        int count1 = 0;
        count1 = sum1 * provinceIndustryHeatValueSection5Value + provinceIndustryHeatValueSection4Value * sum2 + provinceIndustryHeatValueSection3Value * sum3 + provinceIndustryHeatValueSection2Value * sum4 + provinceIndustryHeatValueSection1Value * sum5;

        // 计算行业热度指数 热度指数=区间值n/区间差n*（行业热度值-热度值n）+赋值(n-1)
        float count2 = 0;
        if (count1 >= 20000 && count1 <= 50000) {
            count2 = (float) 10 / 30000 * (count1 - 20000) + provinceIndustryHeatIndexSection4Value;
        }
        if (count1 >= 10000 && count1 <= 19999) {
            count2 = (float) 15 / 9999 * (count1 - 10000) + provinceIndustryHeatIndexSection3Value;
        }
        if (count1 >= 5000 && count1 <= 9999) {
            count2 = (float) 15 / 4999 * (count1 - 5000) + provinceIndustryHeatIndexSection2Value;
        }
        if (count1 >= 1000 && count1 <= 4999) {
            count2 = (float) 20 / 3999 * (count1 - 1000) + provinceIndustryHeatIndexSection1Value;
        }
        if (count1 >= 0 && count1 <= 999) {
            count2 = (float) 40 / 999 * (count1 - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count2));
    }

    /**
     * 计算行业敏感指数
     * 
     * @param provinceId
     * @return
     */
    public float sensitiveIndexByProvinceIdAndIndustry(int provinceId, int eventId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("eventId", eventId);

        // 统计行业的去重前数据
        List<Map> evnewNewsProvinceIdList = eventnewsService.CountByIndustry(map);

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provinceIndustrysensitiveIndexSection1Value = Integer.parseInt(pro.getProperty("provinceIndustrysensitiveIndexSection1Value"));
        int provinceIndustrysensitiveIndexSection2Value = Integer.parseInt(pro.getProperty("provinceIndustrysensitiveIndexSection2Value"));
        int provinceIndustrysensitiveIndexSection3Value = Integer.parseInt(pro.getProperty("provinceIndustrysensitiveIndexSection3Value"));
        int provinceIndustrysensitiveIndexSection4Value = Integer.parseInt(pro.getProperty("provinceIndustrysensitiveIndexSection4Value"));

        // 计算日度敏感指数值 敏感指数=区间值n/区间差n*（去重前数量-热度值n）+赋值(n-1)
        float count3 = 0;
        for (int i = 0; i < evnewNewsProvinceIdList.size(); i++) {
            Map remap = evnewNewsProvinceIdList.get(i);
            int count = new Long((Long) remap.get("count")).intValue();
            if (count >= 10000) {
                count3 = (float) 3 / 20000 * (count - 10000) + provinceIndustrysensitiveIndexSection4Value;
            } else if (count >= 5000) {
                count3 = (float) 4 / 4999 * (count - 5000) + provinceIndustrysensitiveIndexSection3Value;
            } else if (count >= 1000) {
                count3 = (float) 3 / 3999 * (count - 1000) + provinceIndustrysensitiveIndexSection2Value;
            } else if (count >= 300) {
                count3 = (float) 20 / 699 * (count - 300) + provinceIndustrysensitiveIndexSection1Value;
            } else if (count >= 1) {
                count3 = (float) 70 / 299 * (count - 0);
            }
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count3));
    }

    /**
     * 计算行业关注指数
     * 
     * @param provinceId
     * @return
     */
    public float focusIndexByIndustry(int provinceId, int eventId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("eventId", eventId);

        // 统计行业的自媒体数据量
        List<Map> evnewNewsProvinceIdMediaList = eventnewsService.CountByIndustryMedia(map);

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provinceIndustryfocusIndexSection1Value = Integer.parseInt(pro.getProperty("provinceIndustryfocusIndexSection1Value"));
        int provinceIndustryfocusIndexSection2Value = Integer.parseInt(pro.getProperty("provinceIndustryfocusIndexSection2Value"));
        int provinceIndustryfocusIndexSection3Value = Integer.parseInt(pro.getProperty("provinceIndustryfocusIndexSection3Value"));
        int provinceIndustryfocusIndexSection4Value = Integer.parseInt(pro.getProperty("provinceIndustryfocusIndexSection4Value"));

        // 计算日度关注指数值 关注指数=区间值n/区间差n*（自媒体数据量-热度值n）+赋值(n-1)
        float count4 = 0;
        for (int i = 0; i < evnewNewsProvinceIdMediaList.size(); i++) {
            Map remap = evnewNewsProvinceIdMediaList.get(i);
            int count = new Long((Long) remap.get("count")).intValue();

            if (count >= 10000) {
                count4 = (float) 5 / 20000 * (count - 10000) + provinceIndustryfocusIndexSection4Value;
            } else if (count >= 5000) {
                count4 = (float) 5 / 4999 * (count - 5000) + provinceIndustryfocusIndexSection3Value;
            } else if (count >= 1000) {
                count4 = (float) 5 / 3999 * (count - 1000) + provinceIndustryfocusIndexSection2Value;
            } else if (count >= 300) {
                count4 = (float) 5 / 699 * (count - 300) + provinceIndustryfocusIndexSection1Value;
            } else if (count >= 1) {
                count4 = (float) 80 / 299 * (count - 0);
            }
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count4));
    }

    /**
     * 计算媒体关注指数
     * 
     * @param provinceId
     * @return
     */
    public float mediaAttentionIndex(int provinceId, String groupSeedId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("groupSeedId", groupSeedId);

        // 计算媒体关注值 = 重点媒体关注值 + 普通媒体关注值
        float count3 = 0; // 媒体关注值
        float count4 = 0; // 重点媒体关注值
        float count5 = 0; // 普通媒体关注值
        float count6 = 0; // 媒体关注指数

        List<Eventnews> eventnewsNewsList = eventnewsService.CountByEventAndProvinceId(map);

        for (int i = 0; i < eventnewsNewsList.size(); i++) {
            String webSite = eventnewsNewsList.get(i).getWebsite();
            int type = eventnewsNewsList.get(i).getType();
            if (type == 1) {
                if ("中新网".equals(webSite) || "新华网".equals(webSite) || "央视网".equals(webSite) || "人民网".equals(webSite) || "光明网".equals(webSite) || "新浪".equals(webSite) || "腾讯".equals(webSite) || "凤凰".equals(webSite) || "网易".equals(webSite) || "搜狐".equals(webSite) || "澎湃新闻".equals(webSite)) {
                    count4++;
                } else {
                    count5++;
                }
            }
        }

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provinceMediaAttentionIndexKeyMediaValue = Integer.parseInt(pro.getProperty("provinceMediaAttentionIndexKeyMediaValue"));
        int provinceMediaAttentionIndexGeneralMediaValue = Integer.parseInt(pro.getProperty("provinceMediaAttentionIndexGeneralMediaValue"));

        int provinceMediaAttentionIndexSection1Value = Integer.parseInt(pro.getProperty("provinceMediaAttentionIndexSection1Value"));
        int provinceMediaAttentionIndexSection2Value = Integer.parseInt(pro.getProperty("provinceMediaAttentionIndexSection2Value"));
        int provinceMediaAttentionIndexSection3Value = Integer.parseInt(pro.getProperty("provinceMediaAttentionIndexSection3Value"));

        count3 = count4 * provinceMediaAttentionIndexKeyMediaValue + count5 * provinceMediaAttentionIndexGeneralMediaValue;

        // 计算媒体关注指数
        if (count3 >= 5000) {
            count6 = (float) 20 / 40000 * (count3 - 5000) + provinceMediaAttentionIndexSection3Value;
        } else if (count3 >= 1000) {
            count6 = (float) 20 / 1999 * (count3 - 1000) + provinceMediaAttentionIndexSection2Value;
        } else if (count3 >= 500) {
            count6 = (float) 20 / 499 * (count3 - 500) + provinceMediaAttentionIndexSection1Value;
        } else if (count3 >= 1) {
            count6 = (float) 40 / 499 * (count3 - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count6));
    }

    /**
     * 计算网民关注指数
     * 
     * @param provinceId
     * @return
     */
    public float netizensAttentionIndex(int provinceId, String groupSeedId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("groupSeedId", groupSeedId);

        // 计算网民关注值
        float count3 = 0; // 重点论坛网民关注值
        float count4 = 0; // 普通论坛网民关注值
        float count5 = 0; // 博客网民关注值
        float count6 = 0; // 微博网民关注值
        float count7 = 0; // 微信网民关注值

        float count10 = 0; // 重点论坛数量
        float count11 = 0; // 非重点论坛数量
        float count12 = 0; // 博客数量
        float count13 = 0; // 微博数量
        float count14 = 0; // 微信数量
        // 查询eventnewsList-------------------- and e.webSite in
        // ('网易论坛','新华网论坛','大河论坛','华声论坛','红网论坛','彩龙论坛','天涯论坛','强国社区','凯迪社区','中华网社区','百度贴吧','猫扑')
        // and e.type NOT in (1,5,6)
        List<Eventnews> evnewsNewsList = eventnewsService.CountByEventAndProvinceId(map);
        for (int i = 0; i < evnewsNewsList.size(); i++) {

            String webSite = evnewsNewsList.get(i).getWebsite();
            int type = evnewsNewsList.get(i).getType();

            if (type != 1 && type != 5 && type != 6) {
                if (type == 2) {
                    if ("网易论坛".equals(webSite) || "新华网论坛".equals(webSite) || "大河论坛".equals(webSite) || "华声论坛".equals(webSite) || "红网论坛".equals(webSite) || "彩龙论坛".equals(webSite) || "天涯论坛".equals(webSite) || "强国社区".equals(webSite) || "凯迪社区".equals(webSite) || "中华网社区".equals(webSite) || "百度贴吧".equals(webSite) || "猫扑".equals(webSite)) {
                        count10++;
                    } else {
                        count11++;
                    }
                }
            }
            if (type == 4) {
                count12++;
            } else if (type == 3) {
                count13++;
            } else if (type == 7) {
                count14++;
            }
        }

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int provinceNetizensAttentionIndexKeyForumValue = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexKeyForumValue"));
        int provinceNetizensAttentionIndexGeneralForumValue = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexGeneralForumValue"));
        int provinceNetizensAttentionIndexBlogValue = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexBlogValue"));
        int provinceNetizensAttentionIndexWeiBoValue = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexWeiBoValue"));
        int provinceNetizensAttentionIndexWeChatValue = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexWeChatValue"));

        int provinceNetizensAttentionIndexSection1Value = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexSection1Value"));
        int provinceNetizensAttentionIndexSection2Value = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexSection2Value"));
        int provinceNetizensAttentionIndexSection3Value = Integer.parseInt(pro.getProperty("provinceNetizensAttentionIndexSection3Value"));

        count3 = (float) provinceNetizensAttentionIndexKeyForumValue * count10;
        count4 = (float) provinceNetizensAttentionIndexGeneralForumValue * count11;
        count5 = (float) provinceNetizensAttentionIndexBlogValue * count12;
        count6 = (float) provinceNetizensAttentionIndexWeiBoValue * count13;
        count7 = (float) provinceNetizensAttentionIndexWeChatValue * count14;

        // 计算个类型的网民关注值之和
        float count8 = count3 + count4 + count5 + count6 + count7;

        // 计算网民关注指数
        float count9 = 0;
        if (count8 >= 8000) {
            count9 = (float) 20 / 42000 * (count8 - 8000) + provinceNetizensAttentionIndexSection3Value;
        } else if (count8 >= 3000) {
            count9 = (float) 10 / 4999 * (count8 - 3000) + provinceNetizensAttentionIndexSection2Value;
        } else if (count8 >= 1000) {
            count9 = (float) 20 / 1999 * (count8 - 1000) + provinceNetizensAttentionIndexSection1Value;
        } else if (count8 >= 1) {
            count9 = (float) 50 / 999 * (count8 - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count9));
    }

    /**
     * 计算市级热度指数
     * 
     * @param provinceId
     * @return
     */
    public float heatIndexByRegionId(int provinceId, int regionId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("regionId", regionId);

        List<Map> eventNewsList = eventnewsService.CountByGroupCountByRegion(map);
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;

        // 统计信息重复量区间的具体信息量个数（和）
        for (int i = 0; i < eventNewsList.size(); i++) {
            Map remap = eventNewsList.get(i);
            int groupCount = (Integer) remap.get("groupCount");
            int count = new Long((Long) remap.get("count")).intValue();

            if (groupCount >= 3000) {
                sum1 += count;
            } else if (groupCount >= 1000) {
                sum2 += count;
            } else if (groupCount >= 500) {
                sum3 += count;
            } else if (groupCount >= 100) {
                sum4 += count;
            } else if (groupCount >= 1) {
                sum5 += count;
            }
        }

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int cityHeatValueSection1Value = Integer.parseInt(pro.getProperty("cityHeatValueSection1Value"));
        int cityHeatValueSection2Value = Integer.parseInt(pro.getProperty("cityHeatValueSection2Value"));
        int cityHeatValueSection3Value = Integer.parseInt(pro.getProperty("cityHeatValueSection3Value"));
        int cityHeatValueSection4Value = Integer.parseInt(pro.getProperty("cityHeatValueSection4Value"));
        int cityHeatValueSection5Value = Integer.parseInt(pro.getProperty("cityHeatValueSection5Value"));

        int cityHeatIndexSection1Value = Integer.parseInt(pro.getProperty("cityHeatIndexSection1Value"));
        int cityHeatIndexSection2Value = Integer.parseInt(pro.getProperty("cityHeatIndexSection2Value"));
        int cityHeatIndexSection3Value = Integer.parseInt(pro.getProperty("cityHeatIndexSection3Value"));
        int cityHeatIndexSection4Value = Integer.parseInt(pro.getProperty("cityHeatIndexSection4Value"));

        // 计算市级热度值 信息量个数区间在1-99之间
        // 赋分值是0,100-499之间赋分值是20，500-999之间是100,1000-2999之间是500,3000以上是3000
        int count1 = 0;
        count1 = sum1 * cityHeatValueSection5Value + cityHeatValueSection4Value * sum2 + cityHeatValueSection3Value * sum3 + cityHeatValueSection2Value * sum4 + cityHeatValueSection1Value * sum5;

        // 计算市级热度指数 热度指数=区间值n/区间差n*（省域热度值-热度值n）+赋值(n-1)
        float count2 = 0;
        if (count1 >= 20000 && count1 <= 50000) {
            count2 = (float) 10 / 30000 * (count1 - 20000) + cityHeatIndexSection4Value;
        }
        if (count1 >= 10000 && count1 <= 19999) {
            count2 = (float) 15 / 9999 * (count1 - 10000) + cityHeatIndexSection3Value;
        }
        if (count1 >= 5000 && count1 <= 9999) {
            count2 = (float) 15 / 4999 * (count1 - 5000) + cityHeatIndexSection2Value;
        }
        if (count1 >= 1000 && count1 <= 4999) {
            count2 = (float) 20 / 3999 * (count1 - 1000) + cityHeatIndexSection1Value;
        }
        if (count1 >= 0 && count1 <= 999) {
            count2 = (float) 40 / 999 * (count1 - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count2));
    }

    /**
     * 计算市级敏感指数
     * 
     * @param provinceId
     * @return
     */
    public float sensitiveIndexByRegionId(int provinceId, int regionId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("regionId", regionId);

        // 统计市的去重前数据
        List<Map> evnewNewsRegionList = eventnewsService.CountByRegion(map);

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int citysensitiveIndexSection1Value = Integer.parseInt(pro.getProperty("citysensitiveIndexSection1Value"));
        int citysensitiveIndexSection2Value = Integer.parseInt(pro.getProperty("citysensitiveIndexSection2Value"));
        int citysensitiveIndexSection3Value = Integer.parseInt(pro.getProperty("citysensitiveIndexSection3Value"));
        int citysensitiveIndexSection4Value = Integer.parseInt(pro.getProperty("citysensitiveIndexSection4Value"));
        int citysensitiveIndexSection5Value = Integer.parseInt(pro.getProperty("citysensitiveIndexSection5Value"));
        int citysensitiveIndexSection6Value = Integer.parseInt(pro.getProperty("citysensitiveIndexSection6Value"));

        // 计算日度敏感指数值 敏感指数=区间值n/区间差n*（去重前数量-热度值n）+赋值(n-1)
        float count3 = 0;
        for (int i = 0; i < evnewNewsRegionList.size(); i++) {
            Map remap = evnewNewsRegionList.get(i);
            int count = new Long((Long) remap.get("count")).intValue();
            if (count >= 100000) {
                count3 = (float) 5 / 50000 * (count - 100000) + citysensitiveIndexSection6Value;
            } else if (count >= 50000) {
                count3 = (float) 5 / 49999 * (count - 50000) + citysensitiveIndexSection5Value;
            } else if (count >= 20000) {
                count3 = (float) 10 / 25000 * (count - 25000) + citysensitiveIndexSection4Value;
            } else if (count >= 15000) {
                count3 = (float) 10 / 9999 * (count - 15000) + citysensitiveIndexSection3Value;
            } else if (count >= 10000) {
                count3 = (float) 10 / 4999 * (count - 10000) + citysensitiveIndexSection2Value;
            } else if (count >= 5000) {
                count3 = (float) 10 / 4999 * (count - 5000) + citysensitiveIndexSection1Value;
            } else if (count >= 1) {
                count3 = (float) 50 / 4999 * (count - 0);
            }
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count3));
    }

    /**
     * 计算市级关注指数
     * 
     * @param provinceId
     * @return
     */
    public float focusIndexByRegion(int provinceId, int regionId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("regionId", regionId);

        // 统计市级的自媒体数据量
        List<Map> evnewNewsRegionIdMediaList = eventnewsService.CountByRegionIdMedia(map);

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int cityfocusIndexSection1Value = Integer.parseInt(pro.getProperty("cityfocusIndexSection1Value"));
        int cityfocusIndexSection2Value = Integer.parseInt(pro.getProperty("cityfocusIndexSection2Value"));
        int cityfocusIndexSection3Value = Integer.parseInt(pro.getProperty("cityfocusIndexSection3Value"));
        int cityfocusIndexSection4Value = Integer.parseInt(pro.getProperty("cityfocusIndexSection4Value"));

        // 计算日度关注指数值 关注指数=区间值n/区间差n*（自媒体数据量-热度值n）+赋值(n-1)
        float count4 = 0;
        for (int i = 0; i < evnewNewsRegionIdMediaList.size(); i++) {
            Map remap = evnewNewsRegionIdMediaList.get(i);
            int count = new Long((Long) remap.get("count")).intValue();

            if (count >= 25000) {
                count4 = (float) 5 / 25000 * (count - 25000) + cityfocusIndexSection4Value;
            } else if (count >= 15000) {
                count4 = (float) 20 / 9999 * (count - 15000) + cityfocusIndexSection3Value;
            } else if (count >= 10000) {
                count4 = (float) 15 / 4999 * (count - 10000) + cityfocusIndexSection2Value;
            } else if (count >= 5000) {
                count4 = (float) 30 / 4999 * (count - 5000) + cityfocusIndexSection1Value;
            } else if (count >= 1) {
                count4 = (float) 30 / 4999 * (count - 0);
            }
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count4));
    }

    /**
     * 计算市级行业热度指数
     * 
     * @param provinceId
     * @return
     */
    public float heatIndexByRegionIdAndIndustry(List<Map> eventNewsList) {

        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;

        // 统计行业信息重复量区间的具体信息量个数（和）
        for (int i = 0; i < eventNewsList.size(); i++) {
            Map remap = eventNewsList.get(i);
            int groupCount = (Integer) remap.get("groupCount");
            int count = new Long((Long) remap.get("count")).intValue();

            if (groupCount >= 3000) {
                sum1 += count;
            } else if (groupCount >= 1000) {
                sum2 += count;
            } else if (groupCount >= 500) {
                sum3 += count;
            } else if (groupCount >= 100) {
                sum4 += count;
            } else if (groupCount >= 1) {
                sum5 += count;
            }
        }

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int cityIndustryHeatValueSection1Value = Integer.parseInt(pro.getProperty("cityIndustryHeatValueSection1Value"));
        int cityIndustryHeatValueSection2Value = Integer.parseInt(pro.getProperty("cityIndustryHeatValueSection2Value"));
        int cityIndustryHeatValueSection3Value = Integer.parseInt(pro.getProperty("cityIndustryHeatValueSection3Value"));
        int cityIndustryHeatValueSection4Value = Integer.parseInt(pro.getProperty("cityIndustryHeatValueSection4Value"));
        int cityIndustryHeatValueSection5Value = Integer.parseInt(pro.getProperty("cityIndustryHeatValueSection5Value"));

        int cityIndustryHeatIndexSection1Value = Integer.parseInt(pro.getProperty("cityIndustryHeatIndexSection1Value"));
        int cityIndustryHeatIndexSection2Value = Integer.parseInt(pro.getProperty("cityIndustryHeatIndexSection2Value"));
        int cityIndustryHeatIndexSection3Value = Integer.parseInt(pro.getProperty("cityIndustryHeatIndexSection3Value"));
        int cityIndustryHeatIndexSection4Value = Integer.parseInt(pro.getProperty("cityIndustryHeatIndexSection4Value"));

        // 计算行业热度值 信息量个数区间在1-99之间
        // 赋分值是0,100-499之间赋分值是20，500-999之间是100,1000-2999之间是500,3000以上是3000
        int count1 = 0;
        count1 = sum1 * cityIndustryHeatValueSection5Value + cityIndustryHeatValueSection4Value * sum2 + cityIndustryHeatValueSection3Value * sum3 + cityIndustryHeatValueSection2Value * sum4 + cityIndustryHeatValueSection1Value * sum5;

        // 计算行业热度指数 热度指数=区间值n/区间差n*（行业热度值-热度值n）+赋值(n-1)
        float count2 = 0;
        if (count1 >= 20000 && count1 <= 50000) {
            count2 = (float) 10 / 30000 * (count1 - 20000) + cityIndustryHeatIndexSection4Value;
        }
        if (count1 >= 10000 && count1 <= 19999) {
            count2 = (float) 15 / 9999 * (count1 - 10000) + cityIndustryHeatIndexSection3Value;
        }
        if (count1 >= 5000 && count1 <= 9999) {
            count2 = (float) 15 / 4999 * (count1 - 5000) + cityIndustryHeatIndexSection2Value;
        }
        if (count1 >= 1000 && count1 <= 4999) {
            count2 = (float) 20 / 3999 * (count1 - 1000) + cityIndustryHeatIndexSection1Value;
        }
        if (count1 >= 0 && count1 <= 999) {
            count2 = (float) 40 / 999 * (count1 - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count2));
    }

    /**
     * 计算市级行业敏感指数
     * 
     * @param provinceId
     * @return
     */
    public float sensitiveIndexByRegionIdAndIndustry(int count) {

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int cityIndustrysensitiveIndexSection1Value = Integer.parseInt(pro.getProperty("cityIndustrysensitiveIndexSection1Value"));
        int cityIndustrysensitiveIndexSection2Value = Integer.parseInt(pro.getProperty("cityIndustrysensitiveIndexSection2Value"));
        int cityIndustrysensitiveIndexSection3Value = Integer.parseInt(pro.getProperty("cityIndustrysensitiveIndexSection3Value"));
        int cityIndustrysensitiveIndexSection4Value = Integer.parseInt(pro.getProperty("cityIndustrysensitiveIndexSection4Value"));

        // 计算日度敏感指数值 敏感指数=区间值n/区间差n*（去重前数量-热度值n）+赋值(n-1)
        float count3 = 0;

        if (count >= 10000) {
            count3 = (float) 3 / 20000 * (count - 10000) + cityIndustrysensitiveIndexSection4Value;
        } else if (count >= 5000) {
            count3 = (float) 4 / 4999 * (count - 5000) + cityIndustrysensitiveIndexSection3Value;
        } else if (count >= 1000) {
            count3 = (float) 3 / 3999 * (count - 1000) + cityIndustrysensitiveIndexSection2Value;
        } else if (count >= 300) {
            count3 = (float) 20 / 699 * (count - 300) + cityIndustrysensitiveIndexSection1Value;
        } else if (count >= 1) {
            count3 = (float) 70 / 299 * (count - 0);
        }

        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count3));
    }

    /**
     * 计算市级行业关注指数
     * 
     * @param provinceId
     * @return
     */
    public float focusIndexByRegionIdAndIndustry(int count) {

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int cityIndustryfocusIndexSection1Value = Integer.parseInt(pro.getProperty("cityIndustryfocusIndexSection1Value"));
        int cityIndustryfocusIndexSection2Value = Integer.parseInt(pro.getProperty("cityIndustryfocusIndexSection2Value"));
        int cityIndustryfocusIndexSection3Value = Integer.parseInt(pro.getProperty("cityIndustryfocusIndexSection3Value"));
        int cityIndustryfocusIndexSection4Value = Integer.parseInt(pro.getProperty("cityIndustryfocusIndexSection4Value"));

        // 计算日度关注指数值 关注指数=区间值n/区间差n*（自媒体数据量-热度值n）+赋值(n-1)
        float count4 = 0;

        if (count >= 10000) {
            count4 = (float) 5 / 20000 * (count - 10000) + cityIndustryfocusIndexSection4Value;
        } else if (count >= 5000) {
            count4 = (float) 5 / 4999 * (count - 5000) + cityIndustryfocusIndexSection3Value;
        } else if (count >= 1000) {
            count4 = (float) 5 / 3999 * (count - 1000) + cityIndustryfocusIndexSection2Value;
        } else if (count >= 300) {
            count4 = (float) 5 / 699 * (count - 300) + cityIndustryfocusIndexSection1Value;
        } else if (count >= 1) {
            count4 = (float) 80 / 299 * (count - 0);
        }

        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count4));
    }

    /**
     * 计算市级媒体关注指数
     * 
     * @param provinceId
     * @return
     */
    public float mediaAttentionIndexByRegionId(int provinceId, int regionId, String groupSeedId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("groupSeedId", groupSeedId);
        map.put("regionId", regionId);

        // 计算媒体关注值 = 重点媒体关注值 + 普通媒体关注值
        float count3 = 0; // 媒体关注值
        float count4 = 0; // 重点媒体关注值
        float count5 = 0; // 普通媒体关注值
        float count6 = 0; // 媒体关注指数

        // 查询eventnewsList
        List<Eventnews> eventnewsNewsList = eventnewsService.CountByEventAndRegionId(map);

        for (int i = 0; i < eventnewsNewsList.size(); i++) {
            String webSite = eventnewsNewsList.get(i).getWebsite();
            int type = eventnewsNewsList.get(i).getType();
            if (type == 1) {
                if ("中新网".equals(webSite) || "新华网".equals(webSite) || "央视网".equals(webSite) || "人民网".equals(webSite) || "光明网".equals(webSite) || "新浪".equals(webSite) || "腾讯".equals(webSite) || "凤凰".equals(webSite) || "网易".equals(webSite) || "搜狐".equals(webSite) || "澎湃新闻".equals(webSite)) {
                    count4++;
                } else {
                    count5++;
                }
            }
        }

        // 获取properties文件中的赋分值
        Properties pro = getProperties();
        int cityMediaAttentionIndexKeyMediaValue = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexKeyMediaValue"));
        int cityMediaAttentionIndexGeneralMediaValue = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexGeneralMediaValue"));

        int cityMediaAttentionIndexSection1Value = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexSection1Value"));
        int cityMediaAttentionIndexSection2Value = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexSection2Value"));
        int cityMediaAttentionIndexSection3Value = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexSection3Value"));

        count3 = count4 * cityMediaAttentionIndexKeyMediaValue + count5 * cityMediaAttentionIndexGeneralMediaValue;

        // 计算媒体关注指数
        if (count3 >= 5000) {
            count6 = (float) 20 / 40000 * (count3 - 5000) + cityMediaAttentionIndexSection3Value;
        } else if (count3 >= 1000) {
            count6 = (float) 20 / 1999 * (count3 - 1000) + cityMediaAttentionIndexSection2Value;
        } else if (count3 >= 500) {
            count6 = (float) 20 / 499 * (count3 - 500) + cityMediaAttentionIndexSection1Value;
        } else if (count3 >= 1) {
            count6 = (float) 40 / 499 * (count3 - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        return Float.parseFloat(df2.format(count6));
    }

    /**
     * 计算市级媒体、网民关注指数
     * 
     * @param provinceId
     * @return
     */
    public Map netizensAttentionIndexByRegionId(int provinceId, int regionId, String groupSeedId, EventnewsService eventnewsService) {

        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -1);
        String stime = stimeformat.format(calender.getTime());
        String etime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("provinceId", provinceId);
        map.put("groupSeedId", groupSeedId);
        map.put("regionId", regionId);

        // 计算网民关注值
        float count3 = 0; // 重点论坛网民关注值
        float count4 = 0; // 普通论坛网民关注值
        float count5 = 0; // 博客网民关注值
        float count6 = 0; // 微博网民关注值
        float count7 = 0; // 微信网民关注值

        float count10 = 0; // 重点论坛数量
        float count11 = 0; // 非重点论坛数量
        float count12 = 0; // 博客数量
        float count13 = 0; // 微博数量
        float count14 = 0; // 微信数量

        // 计算媒体关注值 = 重点媒体关注值 + 普通媒体关注值
        float countMedia = 0; // 媒体关注值
        float countKeyMedia = 0; // 重点媒体关注值
        float countGeneralMedia = 0; // 普通媒体关注值
        float countMediaIndex = 0; // 媒体关注指数

        // 查询eventnewsList
        // ('网易论坛','新华网论坛','大河论坛','华声论坛','红网论坛','彩龙论坛','天涯论坛','强国社区','凯迪社区','中华网社区','百度贴吧','猫扑')
        // and e.type NOT in (1,5,6)
        List<Eventnews> evnewsNewsList = eventnewsService.CountByEventAndRegionId(map);
        for (int i = 0; i < evnewsNewsList.size(); i++) {

            String webSite = evnewsNewsList.get(i).getWebsite();
            int type = evnewsNewsList.get(i).getType();

            if (type != 1 && type != 5 && type != 6) {
                if (type == 2) {
                    if ("网易论坛".equals(webSite) || "新华网论坛".equals(webSite) || "大河论坛".equals(webSite) || "华声论坛".equals(webSite) || "红网论坛".equals(webSite) || "彩龙论坛".equals(webSite) || "天涯论坛".equals(webSite) || "强国社区".equals(webSite) || "凯迪社区".equals(webSite) || "中华网社区".equals(webSite) || "百度贴吧".equals(webSite) || "猫扑".equals(webSite)) {
                        count10++;
                    } else {
                        count11++;
                    }
                }
            }
            if (type == 4) {
                count12++;
            } else if (type == 3) {
                count13++;
            } else if (type == 7) {
                count14++;
            } else if (type == 1) {
                if ("中新网".equals(webSite) || "新华网".equals(webSite) || "央视网".equals(webSite) || "人民网".equals(webSite) || "光明网".equals(webSite) || "新浪".equals(webSite) || "腾讯".equals(webSite) || "凤凰".equals(webSite) || "网易".equals(webSite) || "搜狐".equals(webSite) || "澎湃新闻".equals(webSite)) {
                    countKeyMedia++;
                } else {
                    countGeneralMedia++;
                }
            }
        }

        // 获取properties文件中的媒体赋分值
        Properties pro = getProperties();
        int cityMediaAttentionIndexKeyMediaValue = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexKeyMediaValue"));
        int cityMediaAttentionIndexGeneralMediaValue = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexGeneralMediaValue"));

        int cityMediaAttentionIndexSection1Value = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexSection1Value"));
        int cityMediaAttentionIndexSection2Value = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexSection2Value"));
        int cityMediaAttentionIndexSection3Value = Integer.parseInt(pro.getProperty("cityMediaAttentionIndexSection3Value"));

        countMedia = countKeyMedia * cityMediaAttentionIndexKeyMediaValue + countGeneralMedia * cityMediaAttentionIndexGeneralMediaValue;

        // 计算媒体关注指数
        if (countMedia >= 5000) {
            countMediaIndex = (float) 20 / 40000 * (countMedia - 5000) + cityMediaAttentionIndexSection3Value;
        } else if (countMedia >= 1000) {
            countMediaIndex = (float) 20 / 1999 * (countMedia - 1000) + cityMediaAttentionIndexSection2Value;
        } else if (countMedia >= 500) {
            countMediaIndex = (float) 20 / 499 * (countMedia - 500) + cityMediaAttentionIndexSection1Value;
        } else if (countMedia >= 1) {
            countMediaIndex = (float) 40 / 499 * (countMedia - 0);
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        Map mapIndex = new HashMap();
        mapIndex.put("countMediaIndex", Float.parseFloat(df2.format(countMediaIndex)));

        // 获取properties文件中的网民赋分值
        // Properties pro = getProperties();
        int cityNetizensAttentionIndexKeyForumValue = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexKeyForumValue"));
        int cityNetizensAttentionIndexGeneralForumValue = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexGeneralForumValue"));
        int cityNetizensAttentionIndexBlogValue = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexBlogValue"));
        int cityNetizensAttentionIndexWeiBoValue = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexWeiBoValue"));
        int cityNetizensAttentionIndexWeChatValue = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexWeChatValue"));

        int cityNetizensAttentionIndexSection1Value = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexSection1Value"));
        int cityNetizensAttentionIndexSection2Value = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexSection2Value"));
        int cityNetizensAttentionIndexSection3Value = Integer.parseInt(pro.getProperty("cityNetizensAttentionIndexSection3Value"));

        count3 = (float) cityNetizensAttentionIndexKeyForumValue * count10;
        count4 = (float) cityNetizensAttentionIndexGeneralForumValue * count11;
        count5 = (float) cityNetizensAttentionIndexBlogValue * count12;
        count6 = (float) cityNetizensAttentionIndexWeiBoValue * count13;
        count7 = (float) cityNetizensAttentionIndexWeChatValue * count14;

        // 计算个类型的网民关注值之和
        float count8 = count3 + count4 + count5 + count6 + count7;

        // 计算网民关注指数
        float count9 = 0;
        if (count8 >= 8000) {
            count9 = (float) 20 / 42000 * (count8 - 8000) + cityNetizensAttentionIndexSection3Value;
        } else if (count8 >= 3000) {
            count9 = (float) 10 / 4999 * (count8 - 3000) + cityNetizensAttentionIndexSection2Value;
        } else if (count8 >= 1000) {
            count9 = (float) 20 / 1999 * (count8 - 1000) + cityNetizensAttentionIndexSection1Value;
        } else if (count8 >= 1) {
            count9 = (float) 50 / 999 * (count8 - 0);
        }
        // DecimalFormat df2 = new DecimalFormat("###.00");
        // return Float.parseFloat(df2.format(count9));
        mapIndex.put("count9", Float.parseFloat(df2.format(count9)));
        return mapIndex;
    }

    /**
     * 读取Properties文件
     * 
     * @return
     */
    public Properties getProperties() {
        IndexProperties indexProperties = new IndexProperties();
        String resources = "config/index/index.properties";
        Properties pro = indexProperties.loadProperties(resources);
        return pro;
    }
}
