package com.peony.peonyfront.topic.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.CalendarUtil;
import com.peony.core.common.utils.DateUtils;
import com.peony.peonyfront.briefreport.model.BriefReportTemp;
import com.peony.peonyfront.briefreport.service.BriefreportTempService;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.ExportSupportService;
import com.peony.peonyfront.focus.model.Focus;
import com.peony.peonyfront.focus.model.FocusPage;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.topic.model.Topic;
import com.peony.peonyfront.topic.model.TopicPage;
import com.peony.peonyfront.topic.service.TopicPageService;
import com.peony.peonyfront.topic.service.TopicService;
import com.peony.peonyfront.topickeywords.model.TopicKeywords;
import com.peony.peonyfront.topickeywords.service.TopicKeywordsService;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.util.Snapshot;
import com.peony.peonyfront.util.export.AssignedCell;
import com.peony.peonyfront.util.export.ExportExcel;

/**
 * 专题信息表action
 * 
 * @author lenovo41
 * @date 2014-6-17 上午9:22:59
 */
@Controller
@RequestMapping("/topicpage")
public class TopicPageController extends BaseController {
    @Autowired
    private TopicPageService       topicPageService;
    @Autowired
    private WebDictionaryService   webDictionaryService;  // 订制舆情页面信息来源服务
    @Autowired
    private BriefreportTempService briefreportTempService;
    @Autowired
    private TopicService           topicService;
    @Autowired
    private TopicKeywordsService   topicKeywordsService;

    @Autowired
    protected ExportSupportService exportSupportService;

    /**
     * 专题信息列表
     * 
     * @return
     */
    @RequestMapping(value = "/listTopicPage")
    public ModelAndView listTopicPage(@ModelAttribute("topicPage") TopicPage topicPage, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (topicPage.getDaytime() != null) {
            if ("0".equals(topicPage.getDaytime())) {
                topicPage.setStime(DateUtils.todayDateStr());
                topicPage.setEtime(DateUtils.todayDateStr());
            }
        }
        if ("".equals(topicPage.getStime())) {
            topicPage.setStime(null);
        }
        if ("".equals(topicPage.getEtime())) {
            topicPage.setEtime(null);
        }
        if (topicPage.getEtime() != null) {
            topicPage.setEtime(topicPage.getEtime());
        }
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        // 页面信息来源
        List<WebDictionary> webDictionaryList = webDictionaryService.findAllWebDictionary();
        topicPage.setPageParameter(pageParameter);
        User user = (User) request.getSession().getAttribute("userfront");
        topicPage.setUserid(user.getUserId());
        Pagination<TopicPage> list = topicPageService.selectByPage(topicPage);
        mv.addObject("topicPage", topicPage);
        mv.addObject("list", list);
        mv.addObject("webDictionaryList", webDictionaryList);
        mv.setViewName("topic/list_topicpage");
        return mv;
    }

    /**
     * 删除专题信息
     * 
     * @param ids
     * @param commodity
     * @return
     */
    @RequestMapping(value = "/deleteTopicPage")
    public @ResponseBody int batchDeleteTopicPage(@RequestParam(value = "id", required = true) String id, HttpServletRequest request) {
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        if (topicPageService.deleteByPrimaryKey(id) > 0) {
            int totalPage = 0;
            if ((totalCount - 1) % 10 == 0) {
                totalPage = (totalCount - 1) / 10;
            } else {
                totalPage = (totalCount - 1) / 10 + 1;
            }
            if (pageNo == 0 || totalPage == 0) {
                return 1;
            } else {
                if (pageNo <= totalPage) {
                    return pageNo;
                } else {
                    return totalPage;
                }
            }
        } else {
            return 0;
        }
    }

    /**
     * 删除所选项
     * 
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteTopicPageByIds")
    @ResponseBody
    public int delTopicPageByIds(@RequestParam(value = "ids", required = true) String ids, HttpServletRequest request) {
        String[] topicPageIds = ids.split(",");
        User user = (User) request.getSession().getAttribute("userfront");
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        if (this.topicPageService.delTopicPageByIds(topicPageIds, user.getUserId()) > 0) {
            int totalPage = 0;
            if ((totalCount - topicPageIds.length) % 10 == 0) {
                totalPage = (totalCount - topicPageIds.length) / 10;
            } else {
                totalPage = (totalCount - topicPageIds.length) / 10 + 1;
            }
            if (pageNo <= totalPage) {
                return pageNo;
            } else {
                return totalPage;
            }
        } else {
            return 0;
        }
    }

    /**
     * 查询统计数
     * 
     * @param id
     * @param stime
     * @param etime
     * @return
     */
    @RequestMapping(value = "/selectByCount")
    public ModelAndView selectByCount(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "stime") String stime, @RequestParam(value = "etime") String etime) {
        Map map = new HashMap();
        map.put("topicid", id);
        map.put("stime", stime);
        map.put("etime", etime);
        // 找到两个时间间隔
        long s = CalendarUtil.getDays(etime, stime) + 1;

        String[] times = new String[(int) s];
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.strToDate(stime, "yyyy-MM-dd"));
        for (int i = 0; i < s; i++) {
            times[i] = DateUtils.dateToStr(cal.getTime(), "yyyy-MM-dd");
            cal.add(Calendar.DATE, 1);
        }
        int[] count1 = new int[(int) s];
        int[] count2 = new int[(int) s];
        int[] count3 = new int[(int) s];
        int[] count4 = new int[(int) s];
        int[] count5 = new int[(int) s];
        int[] count6 = new int[(int) s];
        int[] count7 = new int[(int) s];
        int[] zonghe = new int[(int) s];
        // 从开始日期开始遍历
        List<Map> reList = topicPageService.selectByCount(map);
        // 声明最大值和位置
        int max = 0;
        int index = 0;
        int sum = 0;// 总和
        int news = 0;
        int bbs = 0;
        int weibo = 0;
        int blog = 0;
        int journal = 0;
        int twitter = 0;
        int weixin = 0;
        for (int j = 0; j < times.length; j++) {
            // 放置天
            String time = times[j];
            for (int i = 0; i < reList.size(); i++) {
                Map remap = reList.get(i);
                int type = (Integer) remap.get("type");
                int count = new Long((Long) remap.get("count")).intValue();
                String publishDate = (String) remap.get("publishDate");
                if (time.equals(publishDate)) {
                    switch (type) {
                    case 1:
                        count1[j] = count;
                        zonghe[j] += count;
                        sum += count;
                        news += count;
                        break;
                    case 2:
                        count2[j] = count;
                        zonghe[j] += count;
                        sum += count;
                        bbs += count;
                        break;
                    case 3:
                        count3[j] = count;
                        zonghe[j] += count;
                        sum += count;
                        weibo += count;
                        break;
                    case 4:
                        count4[j] = count;
                        zonghe[j] += count;
                        sum += count;
                        blog += count;
                        break;
                    case 5:
                        count5[j] = count;
                        zonghe[j] += count;
                        sum += count;
                        journal += count;
                        break;
                    case 6:
                        count6[j] = count;
                        zonghe[j] += count;
                        sum += count;
                        twitter += count;
                        break;
                    case 7:
                        count7[j] = count;
                        zonghe[j] += count;
                        sum += count;
                        weixin += count;
                        break;
                    default:
                        break;
                    }
                    if (zonghe[j] > max) {
                        max = zonghe[j];
                        index = j;
                    }
                }

            }

        }
        // 计算总量最多的一天
        String fenxi = "通过事件传播总量走势图分析：事件于" + times[index] + "关注度达到最高峰，总量为" + sum + "条，其中新闻" + news + "条，论坛" + bbs + "条，微博" + weibo + "条，博客" + blog + "条，电子报刊" + journal + "条，twitter" + twitter + "条,微信" + weixin + "条。";
        ModelAndView mv = new ModelAndView();
        mv.addObject("count1", Arrays.toString(count1));
        mv.addObject("count2", Arrays.toString(count2));
        mv.addObject("count3", Arrays.toString(count3));
        mv.addObject("count4", Arrays.toString(count4));
        mv.addObject("count5", Arrays.toString(count5));
        mv.addObject("count6", Arrays.toString(count6));
        mv.addObject("count7", Arrays.toString(count7));
        mv.addObject("zonghe", Arrays.toString(zonghe));
        mv.addObject("fenxi", fenxi);
        mv.addObject("mytimes", Arrays.toString(times).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        // 找到两个时间间隔

        // 查询总体
        List<Map> reList1 = topicPageService.selectByMediaCount(map);
        // 总量数组
        String[] result1 = new String[reList1.size()];

        for (int i = 0; i < reList1.size(); i++) {
            Map remap = reList1.get(i);
            int type = (Integer) remap.get("type");
            int count = new Long((Long) remap.get("count")).intValue();
            String typeStr = "";
            switch (type) {
            case 1:
                typeStr = "新闻";
                break;
            case 2:
                typeStr = "论坛";
                break;
            case 3:
                typeStr = "微博";
                break;
            case 4:
                typeStr = "博客";
                break;
            case 5:
                typeStr = "电子报刊";
                break;
            case 6:
                typeStr = "twitter";
                break;
            case 7:
                typeStr = "微信";
                break;
            default:
                typeStr = "未知来源";
                break;
            }
            result1[i] = "{value:" + count + ", name:'" + typeStr + "'}";

        }
        // 查询分类总体
        map.put("type", 1);
        List<Map> xinwenList = topicPageService.selectByMedia(map);
        // 新闻数组
        String[] result2 = new String[xinwenList.size()];
        int[] result3 = new int[xinwenList.size()];
        ListToArrays(xinwenList, result2, result3);
        // 查询分类总体
        map.put("type", 2);
        List<Map> luntanList = topicPageService.selectByMedia(map);
        // 新闻数组
        String[] result4 = new String[luntanList.size()];
        int[] result5 = new int[luntanList.size()];
        ListToArrays(luntanList, result4, result5);
        // 查询分类总体
        map.put("type", 5);
        List<Map> baokanList = topicPageService.selectByMedia(map);
        // 新闻数组
        String[] result6 = new String[baokanList.size()];
        int[] result7 = new int[baokanList.size()];
        ListToArrays(baokanList, result6, result7);
        mv.addObject("result1", Arrays.toString(result1));
        mv.addObject("result2", Arrays.toString(result2));
        mv.addObject("result3", Arrays.toString(result3));
        mv.addObject("result4", Arrays.toString(result4));
        mv.addObject("result5", Arrays.toString(result5));
        mv.addObject("result6", Arrays.toString(result6));
        mv.addObject("result7", Arrays.toString(result7));
        mv.setViewName("topic/list_topiccount");
        return mv;

    }

    @RequestMapping(value = "/selectByMedia")
    public ModelAndView selectByMedia(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "stime") String stime, @RequestParam(value = "etime") String etime) {
        Map map = new HashMap();
        map.put("topicid", id);
        map.put("stime", stime);
        map.put("etime", etime);
        // 找到两个时间间隔

        // 查询总体
        List<Map> reList = topicPageService.selectByMediaCount(map);
        // 总量数组
        String[] result1 = new String[reList.size()];

        for (int i = 0; i < reList.size(); i++) {
            Map remap = reList.get(i);
            int type = (Integer) remap.get("type");
            int count = new Long((Long) remap.get("count")).intValue();
            String typeStr = "";
            switch (type) {
            case 1:
                typeStr = "新闻";
                break;
            case 2:
                typeStr = "论坛";
                break;
            case 3:
                typeStr = "微博";
                break;
            case 4:
                typeStr = "博客";
                break;
            case 5:
                typeStr = "电子报刊";
                break;
            case 6:
                typeStr = "twitter";
                break;
            case 7:
                typeStr = "微信";
                break;
            default:
                break;
            }
            result1[i] = "{value:" + count + ", name:'" + typeStr + "'}";

        }
        // 查询分类总体
        map.put("type", 1);
        List<Map> xinwenList = topicPageService.selectByMedia(map);
        // 新闻数组
        String[] result2 = new String[xinwenList.size()];
        int[] result3 = new int[xinwenList.size()];
        ListToArrays(xinwenList, result2, result3);
        // 查询分类总体
        map.put("type", 2);
        List<Map> luntanList = topicPageService.selectByMedia(map);
        // 新闻数组
        String[] result4 = new String[luntanList.size()];
        int[] result5 = new int[luntanList.size()];
        ListToArrays(luntanList, result4, result5);
        // 查询分类总体
        map.put("type", 5);
        List<Map> baokanList = topicPageService.selectByMedia(map);
        // 新闻数组
        String[] result6 = new String[baokanList.size()];
        int[] result7 = new int[baokanList.size()];
        ListToArrays(baokanList, result6, result7);
        ModelAndView mv = new ModelAndView();
        mv.addObject("result1", Arrays.toString(result1));
        mv.addObject("result2", Arrays.toString(result2));
        mv.addObject("result3", Arrays.toString(result3));
        mv.addObject("result4", Arrays.toString(result4));
        mv.addObject("result5", Arrays.toString(result5));
        mv.addObject("result6", Arrays.toString(result6));
        mv.addObject("result7", Arrays.toString(result7));
        mv.setViewName("topic/list_topicmedia");
        return mv;

    }

    /**
     * 专题信息列表不分页
     * 
     * @return
     */
    @RequestMapping(value = "/selectTopicPages")
    public ModelAndView selectTopicPages(@ModelAttribute("topicPage") TopicPage topicPage, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        topicPage.setUserid(user.getUserId());
        Pagination<TopicPage> list = topicPageService.selectByPage(topicPage);
        mv.addObject("list", list);
        mv.setViewName("topic/list_topicinfopage");
        return mv;
    }

    public static void main(String[] args) {
        String[] times = new String[7];
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.strToDate("2013-02-05", "yyyy-MM-dd"));
        for (int i = 0; i < 7; i++) {

            cal.add(Calendar.DATE, -1);
            times[i] = DateUtils.dateToStr(cal.getTime(), "yyyy-MM-dd");
            System.out.println(times[i] + "----" + i);
        }

    }

    public void ListToArrays(List<Map> list, String[] s1, int[] s2) {
        for (int i = 0; i < list.size(); i++) {
            Map remap = list.get(i);
            String type = (String) remap.get("website");
            int count = new Long((Long) remap.get("count")).intValue();
            s1[i] = "'" + type + "'";
            s2[i] = count;
        }
    }

    /**
     * 导出选择项(word)
     * 
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadByIds/{ids}")
    @ResponseBody
    public void downloadByIds(@PathVariable("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String[] topicPageids = ids.split(",");
        User user = (User) request.getSession().getAttribute("userfront");
        List<TopicPage> listeventNews = this.topicPageService.selectTopicPageByIds(topicPageids, user.getUserId());
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            TopicPage a = listeventNews.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageid()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listeventNews.set(i, a);
        }
        dataMap.put("list", listeventNews);
        dataMap.put("title", "事件专题");
        dataMap.put("name", user.getName());
        Date dt = new Date();
        dataMap.put("date", new Date());
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_事件专题.doc";
        // --模板(从数据库查找所需要模板)
        // BriefReportTemp
        // briefreportTemp=this.briefreportTempService.findTemByUserId(user.getUserId());
        // String template="Template.ftl";
        // if(null!=briefreportTemp){
        // template=briefreportTemp.getAddress();
        // }
        DocumentHandler doc = new DocumentHandler();
        doc.createDoc(fileName, "Template.ftl", dataMap, request, response);
    }

    /**
     * 导出选择项(excel)
     * 
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadExcelByIds/{ids}")
    @ResponseBody
    public Object downloadExcelByIds(@PathVariable("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        String[] topicPageids = ids.split(",");
        User user = (User) request.getSession().getAttribute("userfront");
        List<TopicPage> listeventNews = this.topicPageService.selectTopicPageByIds(topicPageids, user.getUserId());
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            TopicPage a = listeventNews.get(i);
            a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            listeventNews.set(i, a);
        }
        Date dt = new Date();
        // 生成文件名
        List<AssignedCell[]> data = getAssignedCellData(listeventNews);
        String title = "事件专题";
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        return ee.exportExcelByTemplate(title, downName, response, request.getSession(), data, exportSupportService);
    }

    /**
     * 弹出选择时间页面(word)
     * 
     * @return
     */
    @RequestMapping(value = "/toExportByTime/{val}")
    @ResponseBody
    public ModelAndView toExportByTime(@PathVariable("val") String val) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("val", val);
        mv.setViewName("topic/select_eventTime");
        return mv;
    }

    /**
     * 按选择时间下载(word)
     * 
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadBySelectTime")
    @ResponseBody
    public void downloadBySelectTime(TopicPage topicPage, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        topicPage.setStime(topicPage.getExportStime());
        topicPage.setEtime(topicPage.getExportEtime());
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        topicPage.setUserid(user.getUserId());
        List<TopicPage> listeventNews = this.topicPageService.selectTopicPageBySelectTime(topicPage);
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            TopicPage a = listeventNews.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageid()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listeventNews.set(i, a);
        }
        Date dt = new Date();
        dataMap.put("list", listeventNews);
        dataMap.put("date", dt);
        dataMap.put("title", "事件专题");
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_事件专题.doc";
        // --模板(从数据库查找所需要模板)
        // BriefReportTemp
        // briefreportTemp=this.briefreportTempService.findTemByUserId(user.getUserId());
        // String template="";
        // if(null!=briefreportTemp){
        // template=briefreportTemp.getAddress();
        // }else{
        // template="Template.ftl";
        // }
        DocumentHandler doc = new DocumentHandler();
        doc.createDoc(fileName, "Template.ftl", dataMap, request, response);
    }

    /**
     * 按选择时间下载(excel)
     * 
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadExcelBySelectTime")
    @ResponseBody
    public Object downloadExcelBySelectTime(TopicPage topicPage, HttpServletRequest request, HttpServletResponse response) {
        topicPage.setStime(topicPage.getExportStime());
        topicPage.setEtime(topicPage.getExportEtime());
        // map内放模板需要的数据
        User user = (User) request.getSession().getAttribute("userfront");
        topicPage.setUserid(user.getUserId());
        List<TopicPage> listeventNews = this.topicPageService.selectTopicPageBySelectTime(topicPage);
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            TopicPage a = listeventNews.get(i);
            a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            listeventNews.set(i, a);
        }
        Date dt = new Date();
        // 生成文件名
        List<AssignedCell[]> data = getAssignedCellData(listeventNews);
        String title = "事件专题";
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        return ee.exportExcelByTemplate(title, downName, response, request.getSession(), data, exportSupportService);
    }

    /**
     * 获取excel里要输入的数据
     * 
     * @param listeventNews
     * @return
     */
    private List<AssignedCell[]> getAssignedCellData(List<TopicPage> listeventNews) {
        List<AssignedCell[]> data = new ArrayList<AssignedCell[]>();
        // 添加数据
        for (TopicPage e : listeventNews) {
            String polarity = "";
            if (e.getPolarity() == -1) {
                polarity = "负面";
            } else if (e.getPolarity() == 0) {
                polarity = "争议";
            } else {
                polarity = "正面";
            }
            // System.out.println(e.getPolarity()+":"+polarity);
            AssignedCell[] row1 = { new AssignedCell(0, 0, e.getTitle(), 0), new AssignedCell(0, 1, e.getSummary(), 0), new AssignedCell(0, 2, e.getUrl(), 0), new AssignedCell(0, 3, e.getWebsite(), 0), new AssignedCell(0, 4, DateUtils.dateToStr(e.getPublishdate(), "yyyy-MM-dd HH:mm:ss"), 0), new AssignedCell(0, 5, polarity, 0), };
            data.add(row1);
        }
        return data;
    }

    /**
     * 点击文章详细进入文章详细页面
     * 
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getFocusPageById/{id}")
    @ResponseBody
    public ModelAndView getFocusPageById(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView();
        TopicPage topicpage = this.topicPageService.selectByPrimaryKey(id);
        mv.addObject("topicpage", topicpage);
        mv.setViewName("topic/info_topicpage");
        return mv;
    }

    /**
     * 获取图片流
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getImg")
    @ResponseBody
    public void getImg(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String img0 = request.getParameter("img0");
        String[] url0 = img0.split(",");
        if (url0.length == 2) {
            String u0 = url0[1];
            dataMap.put("img0", u0);
        } else {
            dataMap.put("img0", "");
        }

        String img1 = request.getParameter("img1");
        String[] url1 = img1.split(",");
        if (url1.length == 2) {
            String u1 = url1[1];
            dataMap.put("img1", u1);
        } else {
            dataMap.put("img1", "");
        }
        String img2 = request.getParameter("img2");
        String[] url2 = img2.split(",");
        if (url2.length == 2) {
            String u2 = url2[1];
            dataMap.put("img2", u2);
        } else {
            dataMap.put("img2", "");
        }
        String img3 = request.getParameter("img3");
        String[] url3 = img3.split(",");
        if (url3.length == 2) {
            String u3 = url3[1];
            dataMap.put("img3", u3);
        } else {
            dataMap.put("img3", "");
        }

        String img4 = request.getParameter("img4");
        String[] url4 = img4.split(",");
        if (url4.length == 2) {
            String u4 = url4[1];
            dataMap.put("img4", u4);
        } else {
            dataMap.put("img4", "");
        }

        // 统计数据
        String count = request.getParameter("statistics");
        dataMap.put("count", count);

        String topicid = request.getParameter("topicid");
        if (topicid != null || !topicid.equals("")) {
            Topic topic = topicService.selectByPrimaryKey(Integer.parseInt(topicid));
            dataMap.put("topic", topic);
        }
        // 开始结束时间
        String etime = request.getParameter("etime");
        String stime = request.getParameter("stime");
        if (etime == null || etime.equals("")) {
            etime = DateUtils.todayDateStr();
        }
        dataMap.put("etime", etime);

        if (stime == null || stime.equals("")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.strToDate(etime, "yyyy-MM-dd"));
            cal.add(Calendar.DATE, -6);
            stime = DateUtils.dateToStr(cal.getTime(), "yyyy-MM-dd");
        }
        dataMap.put("stime", stime);

        User user = (User) request.getSession().getAttribute("userfront");
        if (topicid != null || !topicid.equals("")) {
            TopicPage topicPage = new TopicPage();
            topicPage.setTopicid(Integer.parseInt(topicid));
            topicPage.setUserid(user.getUserId());
            Pagination<TopicPage> list = topicPageService.selectByPage(topicPage);
            // 显示正文、去除html标签
            for (int i = 0; i < list.getList().size(); i++) {
                TopicPage a = list.getList().get(i);
                if (a.getType() != 3 && a.getType() != 6) {
                    a.setSummary(Snapshot.getTestContent(a.getPageid()));
                } else {
                    a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
                }
                list.getList().set(i, a);
            }
            dataMap.put("list", list.getList());
        } else {
            dataMap.put("list", new ArrayList<TopicPage>());
        }

        DocumentHandler doc = new DocumentHandler();
        Date dt = new Date();
        String fileName = user.getName() + "_" + dt.getTime() + "_事件专题.doc";
        doc.createTopicDoc(fileName, "topicTemplate.ftl", dataMap, request, response);
    }
}
