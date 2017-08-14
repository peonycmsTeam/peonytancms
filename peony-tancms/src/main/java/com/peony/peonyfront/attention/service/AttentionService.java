package com.peony.peonyfront.attention.service;

import java.util.List;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.attention.model.Attention;

public interface AttentionService {

    Pagination<Attention> findByPage(Attention attention);

    /**
     * 根据主键查找对象
     *
     * @param attentionId
     * @return
     */
    Attention selectByPrimaryKey(Integer attentionId);

    /**
     * 根据主键删除
     *
     * @param attentionId
     * @return
     */
    int deleteByPrimaryKey(Integer attentionId);

    /**
     * 修改收藏夹名称
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Attention record);

    /**
     * 添加收藏夹
     *
     * @param record
     * @return
     */
    int insertSelective(Attention record);

    List<Attention> findAll(Attention attention);

}
