package com.peony.peonyfront.focus.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.focus.model.FocusPage;

public interface FocusPageService {
    /**
     * 添加舆情专题
     * 
     * @param record
     * @return
     */
    int insertSelective(FocusPage record);

    /**
     * 根据id查询实体类
     */
    FocusPage selectByPrimaryKey(String id);

    /**
     * 修改专题
     */
    int updateByPrimaryKey(FocusPage record);

    /**
     * 删除专题
     * 
     * @param userkeywsId
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 修改专题
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(FocusPage record);

    /**
     * 分页查询
     * 
     * @param record
     * @return
     */
    Pagination<FocusPage> selectByPage(FocusPage record);

    /**
     * 查询汇总
     * 
     * @param id
     * @param sTime
     * @param eTime
     * @return
     */
    List selectByCount(Map map);

    /**
     * 查询分类媒体汇总
     * 
     * @param id
     * @param sTime
     * @param eTime
     * @return
     */
    List selectByMedia(Map map);

    /**
     * 查询媒体汇总
     * 
     * @param id
     * @param sTime
     * @param eTime
     * @return
     */
    List selectByMediaCount(Map map);

    /**
     * 查询汇总
     * 
     * @param id
     * @param sTime
     * @return
     */
    List selectByTypeCount(Map map);

    /**
     * 导出选择的舆情
     * 
     * @param ids
     * @return
     */
    List<FocusPage> selectFocusPageByIds(@Param(value = "ids") String[] ids);

    /**
     * 按时间段导出
     * 
     * @param record
     * @return
     */
    List<FocusPage> selectFocusBySelectTime(FocusPage record);
}
