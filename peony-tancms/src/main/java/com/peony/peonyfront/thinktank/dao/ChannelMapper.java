package com.peony.peonyfront.thinktank.dao;

import java.util.List;

import com.peony.peonyfront.thinktank.model.Channel;

public interface ChannelMapper {
    int deleteByPrimaryKey(Integer channelId);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer channelId);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);

    /**
     * 查询Channel分页
     * 
     * @param channel
     * @return
     */
    List<Channel> selectChannelByPage(Channel channel);

    /**
     * 查询案例库下所有子节点
     * 
     * @param channel
     * @return
     */
    List<Channel> selectChannelByPid(Channel channel);
}