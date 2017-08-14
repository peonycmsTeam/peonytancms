package com.peony.peonyfront.focus.service;

import java.util.List;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.focus.model.Focus;

public interface FocusService {
    /**
     * 添加舆情专题
     * 
     * @param record
     * @return
     */
    int insertSelective(Focus record);

    /**
     * 根据id查询实体类
     */
    Focus selectByPrimaryKey(String id);

    /**
     * 修改专题
     */
    int updateByPrimaryKey(Focus record);

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
    int updateByPrimaryKeySelective(Focus record);

    /**
     * 分页查询
     * 
     * @param record
     * @return
     */
    Pagination<Focus> selectByPage(Focus record);
}
