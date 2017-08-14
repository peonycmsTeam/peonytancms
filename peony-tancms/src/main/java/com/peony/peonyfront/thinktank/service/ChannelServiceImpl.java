package com.peony.peonyfront.thinktank.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.thinktank.dao.ChannelMapper;
import com.peony.peonyfront.thinktank.model.Channel;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Resource
    private ChannelMapper channelMapper;

    @Override
    public Pagination<Channel> selectChannelByPage(Channel channel) {
        List<Channel> channels = this.channelMapper.selectChannelByPage(channel);
        Pagination pagination = new Pagination<Channel>(channels, channel.getPageParameter());
        pagination.setTotalCount(channels.size());
        return pagination;
    }

    @Override
    public List<Channel> selectChannelByPid(Channel channel) {
        return this.channelMapper.selectChannelByPid(channel);
    }

    @Override
    public Channel selectByPrimaryKey(Integer channelId) {
        return this.channelMapper.selectByPrimaryKey(channelId);
    }

}
