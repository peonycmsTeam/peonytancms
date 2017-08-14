package com.peony.peonyfront.mediacount.service;

import java.util.List;

import com.peony.peonyfront.mediacount.model.MediaCount;

public interface MediaCountService {

    /**
     * 插入mediaCount
     * 
     * @param mediaCount
     * @return
     */
    public int insert(MediaCount mediaCount);

    /**
     * 查询日期为昨天的mediaCount
     * 
     * @param record
     * @return
     */
    List<MediaCount> selectMediaCountByDate(String time);

    /**
     * 查询当月总条数
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectMediaoneByDate(MediaCount mediacount);

    /**
     * 查询近七天总条数
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectMediaoneByDateZhou(MediaCount mediacount);

    /**
     * 查询上月总条数
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectMediaoneByDateYue(MediaCount mediacount);

    /**
     * 各类型媒体排行
     * 
     * @param mediacount
     * @return
     */
    List<MediaCount> selectMediaByDate(MediaCount mediacount);

    /**
     * 查询论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDate(MediaCount mediacount);

    /**
     * 查询博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDate(MediaCount mediacount);

    /**
     * 以下是查询市 各媒体条数
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectWeixinByDates(MediaCount mediacount);

    /**
     * 以下是查询市 条数
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectWeixinByDatesZhou(MediaCount mediacount);

    /**
     * 以下是查询市 条数
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectWeixinByDatesYue(MediaCount mediacount);

    /**
     * 查询全省新闻 媒体排行
     */
    List<MediaCount> selectMediaByDateSheng(MediaCount mediacount);

    /**
     * 查询全省论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDateSheng(MediaCount medincount);

    /**
     * 查询全省博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDateSheng(MediaCount mediacount);

    /**
     * 查询全国新闻 媒体排行
     */
    List<MediaCount> selectMediaByDateZhou(MediaCount mediacount);

    /**
     * 查询全国论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDateZhou(MediaCount medincount);

    /**
     * 查询全国博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDateZhou(MediaCount mediacount);

    /**
     * 查询全国新闻 媒体排行
     */
    List<MediaCount> selectMediaByDateYue(MediaCount mediacount);

    /**
     * 查询全国论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDateYue(MediaCount medincount);

    /**
     * 查询全国博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDateYue(MediaCount mediacount);

    /**
     * 查询省内新闻 媒体排行
     */
    List<MediaCount> selectMediaByDateShengZhou(MediaCount mediacount);

    /**
     * 查询省内论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDateShengZhou(MediaCount medincount);

    /**
     * 查询省内博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDateShengZhou(MediaCount mediacount);

    /**
     * 查询省内新闻 媒体排行
     */
    List<MediaCount> selectMediaByDateShengYue(MediaCount mediacount);

    /**
     * 查询省内论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDateShengYue(MediaCount medincount);

    /**
     * 查询省内博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDateShengYue(MediaCount mediacount);

}
