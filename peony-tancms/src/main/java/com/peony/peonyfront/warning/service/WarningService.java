package com.peony.peonyfront.warning.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.warning.model.Warning;

public interface WarningService {
    /**
     * 查询舆情预警列表
     * 
     * @param warning
     * @return
     */
    Pagination<Warning> selectWaringByPage(Warning warning);

    /**
     * 根据id删除预警
     * 
     * @param waringIds
     * @return
     */
    int delWaringByWaringIds(@Param(value = "waringIds") String[] waringIds);

    /**
     * 定制舆情加入预警
     * 
     * @param pageId
     * @return
     */
    int saveSubjectPage(Warning warning);

    /**
     * 根据id查询预警信息
     * 
     * @param warningId
     * @return
     */
    Warning selectByPrimaryKey(String warningId);

    /**
     * 未读置为已读
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Warning record);

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
