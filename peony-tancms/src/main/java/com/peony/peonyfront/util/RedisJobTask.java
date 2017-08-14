package com.peony.peonyfront.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.peony.core.base.pojo.PageParameter;
import com.peony.core.common.utils.CalendarUtil;
import com.peony.core.common.utils.DateUtils;
import com.peony.core.common.utils.JsonUtil;
import com.peony.core.common.utils.tedis.TedisUtil;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.model.Subscription;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.event.service.SubscriptionService;
import com.peony.peonyfront.homepage.controller.model.Statistics;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.login.service.UserService;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.SubjectService;

public class RedisJobTask implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(RedisJobTask.class);

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private SubjectService      subjectService;

    @Autowired
    private SubjectPageService  subjectPageService;

    @Autowired
    private TedisUtil           tedisUtil;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private RegionService       regionService;

    @Autowired
    private UserService         userService;

    @Autowired
    private EventnewsService    eventnewsService;

    /**
     * 从数据库中读取活跃用户
     */
    public void doUserRedis() {
        OperationLog operationLog = new OperationLog();
        dealTime(operationLog);
        List<OperationLog> list = operationLogService.selectTop20(operationLog);
        if (CollectionUtils.isNotEmpty(list)) {
            try {
                tedisUtil.batchDel("SubjectPage");
                tedisUtil.batchDel("Eventnews");
            } catch (Exception e) {
                logger.error("", e);
            }
            toListSubjectSentiment(list);
        }
    }

    /**
     * 用来创建用户缓存
     */
    public void toListSubjectSentiment(List<OperationLog> list) {
        for (int i = 0; i < list.size(); i++) {
            int userId = list.get(i).getUserId();
            logger.info("当前缓存用户ID " + userId);
            User user = this.userService.selectByPrimaryKey(userId);
            if ("2".equals(user.getUserType())) {// 政府
                // 创建政务舆情缓存
                doListEventnews(userId);
                // doMobileListEventnews(userId);
            }
            doListSubjectSentiment(userId);// pc
            doIphoneMobileListSubjectSentiment(userId);// iphone
            doAndroidPadListSubjectSentiment(userId);// android pad
            doMobileListSubjectSentiment(userId);// 安卓手机
            // 创建首页统计缓存
            doHomeTongji(userId);
        }
    }

    /**
     * 创建首页统计缓存
     * 
     * @param userid
     */
    public void doHomeTongji(int userid) {
        String key = userid + "tongji";
        Date endDate = new Date(); // 当前时间
        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        String etime = stimeformat.format(endDate);

        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -6);// 统计7天数据
        String stime = stimeformat.format(calender.getTime());

        Map map = new HashMap();
        map.put("beginTime", stime);
        map.put("endTime", etime);
        map.put("userid", userid);
        // 找到两个时间间隔
        long s = CalendarUtil.getDays(etime, stime) + 1;

        String[] times = new String[(int) s];
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.strToDate(stime, "yyyy-MM-dd"));
        for (int i = 0; i < s; i++) {
            times[i] = DateUtils.dateToStr(cal.getTime(), "yyyy-MM-dd");
            cal.add(Calendar.DATE, 1);
        }
        int[] count1 = new int[(int) s];// 正面
        int[] count2 = new int[(int) s];// 负面
        int[] count3 = new int[(int) s];// 争议

        int allcount1 = 0;// 正面总数
        int allcount2 = 0;// 负面总数
        int allcount3 = 0;// 争议总数
        // 从开始日期开始遍历
        List<Map> reList = this.subjectPageService.countSelectByCount(map);
        // 声明最大值和位置
        for (int j = 0; j < times.length; j++) {

            String time = times[j];
            for (int i = 0; i < reList.size(); i++) {
                Map remap = reList.get(i);
                int type = (Integer) remap.get("polarity");
                int count = new Long((Long) remap.get("count")).intValue();
                String publishDate = (String) remap.get("publishDate");
                if (time.equals(publishDate)) {
                    switch (type) {
                    case 1:
                        count1[j] = count;
                        allcount1 += count;
                        break;
                    case -1:
                        count2[j] = count;
                        allcount2 += count;
                        break;
                    case 0:
                        count3[j] = count;
                        allcount3 += count;
                        break;
                    default:
                        break;
                    }

                }
            }

        }
        Statistics statistics = new Statistics();
        statistics.setCount1(Arrays.toString(count1));
        statistics.setCount2(Arrays.toString(count2));
        statistics.setCount3(Arrays.toString(count3));
        statistics.setMytimes(Arrays.toString(times).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        statistics.setAllcount1(Integer.toString(allcount1));
        statistics.setAllcount2(Integer.toString(allcount2));
        statistics.setAllcount3(Integer.toString(allcount3));
        try {
            tedisUtil.tedisSetString(key, JsonUtil.toJson(statistics));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建政务舆情缓存
     * 
     * @param userid
     */
    public void doListEventnews(int userid) {
        Eventnews eventnews = new Eventnews();

        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(10);
        eventnews.setPageParameter(pageParameter);
        eventnews.setEventid(-1);
        Subscription subscription = subscriptionService.findSubscriptionByUserId(Integer.toString(userid));
        eventnews.setEventArray(subscription.getEventId());
        List<Region> list = regionService.selectByUserId(userid);
        if (null == list || list.size() == 0 || "".equals(subscription.getEventId())) {
            return;
        } else {
            eventnews.setRegionID(list.get(0).getRegionid().toString());
            if (eventnews.getRegionID().length() > 2) {
                eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
            } else {
                eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID()));
            }
            dealTime("2", eventnews);
            eventnews.setTimeMethod("2");
            eventnewsService.findByPage(eventnews);

            // 首页缓存
            Eventnews ev = new Eventnews();
            pageParameter.setPageSize(7);
            ev.setPageParameter(pageParameter);
            ev.setProvinceid(eventnews.getProvinceid());
            dealTime("3", ev);
            ev.setEventArray(eventnews.getEventArray());
            ev.setRegionID(eventnews.getRegionID());
            ev.setEventid(-1);
            eventnewsService.selectGroupByTitle(ev);
        }
    }

    /**
     * 创建手机政务舆情缓存
     * 
     * @param userid
     */
    public void doMobileListEventnews(int userid) {
        Eventnews eventnews = new Eventnews();

        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(10);
        eventnews.setPageParameter(pageParameter);
        eventnews.setEventid(-1);
        Subscription subscription = subscriptionService.findSubscriptionByUserId(Integer.toString(userid));
        eventnews.setEventArray(subscription.getEventId());
        List<Region> list = regionService.selectByUserId(userid);
        if (null == list || list.size() == 0 || "".equals(subscription.getEventId())) {
            return;
        } else {
            eventnews.setRegionID(list.get(0).getRegionid().toString());
            if (eventnews.getRegionID().length() > 2) {
                eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
            } else {
                eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID()));
            }
            dealTime("3", eventnews);
            eventnewsService.findByPage(eventnews);

        }
    }

    /**
     * 创建订制舆情缓存
     * 
     * @param userid
     */
    public void doListSubjectSentiment(int userid) {
        Subject subject = new Subject();
        subject.setUserid(userid);
        List<Subject> list = subjectService.selectSubjectByUserIdExclusive(subject);
        if (null == list || list.size() == 0) {
            return;
        } else {
            String subjectidArray = "";
            for (int i = 0; i < list.size(); i++) {
                if ("".equals(subjectidArray)) {
                    subjectidArray = list.get(i).getId().toString();
                } else {
                    subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                }

            }
            SubjectPage subjectPage = new SubjectPage();
            if (!"".equals(subjectidArray)) {
                setSubjectPage(subjectPage);
                subjectPage.setUserid(userid);
                subjectPage.setSubjectidArray(subjectidArray);
                subjectPage.setSubjectid(1);
                subjectPageService.findByPage(subjectPage);

                // 首页缓存
                SubjectPage sb = new SubjectPage();
                sb.setPageParameter(subjectPage.getPageParameter());
                sb.setUserid(userid);
                sb.setSubjectidArray(subjectidArray);
                dealTime("3", sb);
                subjectPageService.selectGroupByTitle(sb);
            }
        }
    }

    /**
     * 手机订制舆情缓存
     * 
     * @param userid
     */
    public void doMobileListSubjectSentiment(int userid) {
        Subject subject = new Subject();
        subject.setUserid(userid);
        List<Subject> list = subjectService.selectSubjectByUserIdExclusive(subject);
        if (null == list || list.size() == 0) {
            return;
        } else {
            String subjectidArray = "";
            for (int i = 0; i < list.size(); i++) {
                if ("".equals(subjectidArray)) {
                    subjectidArray = list.get(i).getId().toString();
                } else {
                    subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                }

            }
            SubjectPage subjectPage = new SubjectPage();
            setSubjectPage(subjectPage);
            subjectPage.setSubjectid(1);
            subjectPage.setTime("3");
            subjectPage.setUserid(userid);
            subjectPage.setSubjectidArray(subjectidArray);

            dealTime("3", subjectPage);
            subjectPageService.findByPage(subjectPage);

        }
    }

    /**
     * iphone手机订制舆情缓存 已经能用
     * 
     * @param userid
     */
    public void doIphoneMobileListSubjectSentiment(int userid) {
        Subject subject = new Subject();
        subject.setUserid(userid);
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(20);
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setPageParameter(pageParameter);
        subjectPage.setNewslevelConditions("0,3,4,5");
        subjectPage.setTime("3");
        subjectPage.setType(0);
        subjectPage.setPolarity(-2);
        subjectPage.setUserid(userid);
        dealTime("3", subjectPage);
        subjectPageService.findByPage(subjectPage);
    }

    /**
     * androidpad订制舆情缓存 已经能用
     * 
     * @param userid
     */
    public void doAndroidPadListSubjectSentiment(int userid) {
        Subject subject = new Subject();
        subject.setUserid(userid);
        List<Subject> list = subjectService.selectSubjectByUserIdExclusive(subject);
        if (null == list || list.size() == 0) {
            return;
        } else {
            String subjectidArray = "";
            for (int i = 0; i < list.size(); i++) {
                if ("".equals(subjectidArray)) {
                    subjectidArray = list.get(i).getId().toString();
                } else {
                    subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                }

            }

            SubjectPage subjectPage = new SubjectPage();
            PageParameter pageParameter = new PageParameter();
            pageParameter.setCurrentPage(1);
            pageParameter.setPageSize(15);
            subjectPage.setPageParameter(pageParameter);
            dealTime("2", subjectPage);
            subjectPage.setTime("2");
            subjectPage.setNewslevelConditions("0,3,4,5");
            subjectPage.setSubjectid(1);
            subjectPage.setTime("3");
            subjectPage.setUserid(userid);
            subjectPage.setSubjectidArray(subjectidArray);
            dealTime("3", subjectPage);
            subjectPageService.findByPage(subjectPage);

        }
    }

    /**
     * 日志时间处理
     * 
     * @param operationLog
     */
    private void dealTime(OperationLog operationLog) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        // 当前日期
        operationLog.setEndTime(df.format(new Date()) + " 23:59:59");
        calendar.add(Calendar.DATE, -6);
        Date date = calendar.getTime();
        operationLog.setBeginTime(df.format(date) + " 00:00:00");
    }

    /**
     * 政务舆情时间处理
     * 
     * @param time
     * @param eventnews
     */
    private void dealTime(String time, Eventnews eventnews) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 当前日期
        eventnews.setEndTime(df.format(new Date()) + " 23:59:59");

        Calendar calendar = Calendar.getInstance();

        // 近1天
        if ("1".equals(time)) {
            eventnews.setBeginTime(df.format(new Date()) + " 00:00:00");
        } else if ("2".equals(time)) {
            // 近3天
            calendar.add(Calendar.DATE, -2);
            Date date = calendar.getTime();
            eventnews.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("3".equals(time)) {
            // 近7天
            calendar.add(Calendar.DATE, -6);
            Date date = calendar.getTime();
            eventnews.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("4".equals(time)) {
            // 近1个月
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();
            eventnews.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("5".equals(time)) {
            eventnews.setBeginTime(null);
            eventnews.setEndTime(null);
        }
    }

    /**
     * 清楚缓存
     */
    public void cleanUserRedis() {
        tedisUtil.batchDel("SubjectPage");
        tedisUtil.batchDel("Eventnews");
        tedisUtil.batchDel("tongji");
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {

    }

    public void getSubjectPage() {

    }

    /**
     * 初始化订制舆情
     * 
     * @param subjectPage
     */
    public void setSubjectPage(SubjectPage subjectPage) {
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(10);
        subjectPage.setPageParameter(pageParameter);
        dealTime("2", subjectPage);
        subjectPage.setTime("2");
        subjectPage.setNewslevelConditions("0,3,4,5");
    }

    private void dealTime(String time, SubjectPage subjectPage) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 当前日期
        subjectPage.setEndTime(df.format(new Date()) + " 23:59:59");

        Calendar calendar = Calendar.getInstance();

        // 近1天
        if ("1".equals(time)) {
            subjectPage.setBeginTime(df.format(new Date()) + " 00:00:00");
        } else if ("2".equals(time)) {
            // 近3天
            calendar.add(Calendar.DATE, -2);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("3".equals(time)) {
            // 近7天
            calendar.add(Calendar.DATE, -6);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("4".equals(time)) {
            // 近1个月
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("5".equals(time)) {
            subjectPage.setBeginTime(null);
            subjectPage.setEndTime(null);
        }
    }

}
