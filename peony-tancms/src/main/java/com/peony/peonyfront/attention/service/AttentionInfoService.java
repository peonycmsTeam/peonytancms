package com.peony.peonyfront.attention.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.attention.model.AttentionInfo;

public interface AttentionInfoService {
    /**
     * 根据 attentionId查询attentionInfo列表
     *
     * @param attentionInfo
     * @return
     */
    Pagination<AttentionInfo> selectAttentionInfoByAttentionIdByPage(AttentionInfo attentionInfo);

    /**
     * 根据ids删除选中的attentionInfo
     *
     * @param attentionInfoIds
     * @return
     */
    int delAttentionInfoByAttentionInfoIds(@Param(value = "attentionInfoIds") String[] attentionInfoIds);

    /**
     * 根据attentionInfoId查询对象
     *
     * @param attentionInfoId
     * @return
     */
    AttentionInfo selectByPrimaryKey(Integer attentionInfoId);

    /**
     * 添加收藏夹内容
     *
     * @param record
     * @return
     */
    int insertSelective(AttentionInfo record);

    /**
     * 未读置为已读
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AttentionInfo record);

    /**
     * 根据 attentionId查询attentionInfo列表(不带分页)
     *
     * @param attentionId
     * @return
     */
    List<AttentionInfo> selectAttentionInfoByAttentionId(AttentionInfo record);
}
