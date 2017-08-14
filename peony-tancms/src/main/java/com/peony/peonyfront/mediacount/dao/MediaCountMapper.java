package com.peony.peonyfront.mediacount.dao;

import java.util.List;

import com.peony.peonyfront.mediacount.model.MediaCount;

public interface MediaCountMapper {
    int deleteByPrimaryKey(String id);

    int insert(MediaCount record);

    int insertSelective(MediaCount record);

    MediaCount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MediaCount record);

    int updateByPrimaryKey(MediaCount record);

    List<MediaCount> selectMediaCountByDate(String time);

    /**
     * 查询本月媒体
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectMediaoneByDate(MediaCount mediacount);

    /**
     * 查询近7天媒体
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectMediaoneByDateZhou(MediaCount mediacount);

    /**
     * 查询上月媒体
     * 
     * @param mediacount
     * @return
     */

    List<MediaCount> selectMediaoneByDateYue(MediaCount mediacount);

    /**
     * 查询全国新闻 媒体排行
     */
    List<MediaCount> selectMediaByDate(MediaCount mediacount);

    /**
     * 查询全国论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDate(MediaCount medincount);

    /**
     * 查询全国博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDate(MediaCount mediacount);

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
     * 以下是查询市 条数
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
     * 查询省内新闻 媒体排行
     */
    List<MediaCount> selectMediaByDateSheng(MediaCount mediacount);

    /**
     * 查询省内论坛媒体排行
     */
    List<MediaCount> selectMediaBbsByDateSheng(MediaCount medincount);

    /**
     * 查询省内博客媒体排行
     */
    List<MediaCount> selectMediaBlogByDateSheng(MediaCount mediacount);

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