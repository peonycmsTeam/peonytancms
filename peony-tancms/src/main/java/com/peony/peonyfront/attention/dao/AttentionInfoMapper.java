package com.peony.peonyfront.attention.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.attention.model.AttentionInfo;

public interface AttentionInfoMapper {
    int deleteByPrimaryKey(Integer attentionInfoId);

    int insert(AttentionInfo record);

    int insertSelective(AttentionInfo record);

    AttentionInfo selectByPrimaryKey(Integer attentionInfoId);

    int updateByPrimaryKeySelective(AttentionInfo record);

    int updateByPrimaryKey(AttentionInfo record);

    /**
     * 根据 attentionId查询attentionInfo列表
     *
     * @param attentionId
     * @return
     */
    List<AttentionInfo> selectAttentionInfoByAttentionIdByPage(AttentionInfo record);

    /**
     * 根据 attentionId查询attentionInfo列表(不带分页)
     *
     * @param attentionId
     * @return
     */
    List<AttentionInfo> selectAttentionInfoByAttentionId(AttentionInfo record);

    /**
     * 根据ids删除选中的attentionInfo
     *
     * @param attentionInfoIds
     * @return
     */
    int delAttentionInfoByAttentionInfoIds(@Param(value = "attentionInfoIds") String[] attentionInfoIds);

    /**
     * 删除收藏夹，删除收藏夹下的信息
     *
     * @param attentionId
     * @return
     */
    int delAttentionInfoByAttentionId(Integer attentionId);
}