package com.peony.peonyfront.thinktank.service;

import java.util.List;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.thinktank.model.Channel;

public interface ChannelService {
    /**
     * 查询Channel分页
     * 
     * @param channel
     * @return
     */
    Pagination<Channel> selectChannelByPage(Channel channel);

    /**
     * 查询案例库下所有子节点
     * 
     * @param channel
     * @return
     */
    List<Channel> selectChannelByPid(Channel channel);

    Channel selectByPrimaryKey(Integer channelId);
}
