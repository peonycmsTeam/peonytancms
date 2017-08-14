package com.peony.peonyfront.agent.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.agent.dao.AgentMapper;
import com.peony.peonyfront.agent.model.Agent;

/**
 * 代理商服务
 *
 * @author jhj
 */
@Service
public class AgentServiceImpl implements AgentService {

    @Resource
    private AgentMapper agentMapper;

    @Override
    public Agent selectByPrimaryKey(Integer agentId) {
        return this.agentMapper.selectByPrimaryKey(agentId);
    }

}
