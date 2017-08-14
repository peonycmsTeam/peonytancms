package com.peony.peonyfront.warning.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.warning.model.Warning;

public interface WarningMapper {
    int deleteByPrimaryKey(String warningId);

    int insert(Warning record);

    int insertSelective(Warning record);

    Warning selectByPrimaryKey(String warningId);

    int updateByPrimaryKeySelective(Warning record);

    int updateByPrimaryKey(Warning record);

    /**
     * 查询舆情预警列表
     * 
     * @param warning
     * @return
     */
    List<Warning> selectWaringByPage(Warning warning);

    /**
     * 根据id删除预警
     * 
     * @param waringIds
     * @return
     */
    int delWaringByWaringIds(@Param(value = "waringIds") String[] waringIds);

    /**
     * 今日预警总数
     * 
     * @param userId
     * @return
     */
    int selectWarningTodayCount(@Param(value = "userId") Integer userId);

    /**
     * 根据id导出
     * 
     * @param waringIds
     * @return
     */
    List<Warning> selectByWarningIds(@Param(value = "waringIds") String[] waringIds);

    /**
     * 根据时间查询所有预警
     * 
     * @param warning
     * @return
     */
    List<Warning> selectWaringByTime(Warning warning);
}