package com.peony.peonyfront.focus.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.focus.model.FocusPage;

public interface FocusPageMapper {
    int deleteByPrimaryKey(String id);

    int insert(FocusPage record);

    int insertSelective(FocusPage record);

    FocusPage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FocusPage record);

    int updateByPrimaryKey(FocusPage record);

    List<FocusPage> selectByPage(FocusPage record);

    List selectByCount(Map map);

    List selectByMediaCount(Map map);

    List selectByMedia(Map map);

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