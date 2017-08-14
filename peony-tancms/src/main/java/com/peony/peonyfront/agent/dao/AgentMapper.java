package com.peony.peonyfront.agent.dao;

import com.peony.peonyfront.agent.model.Agent;

public interface AgentMapper {
    int deleteByPrimaryKey(Integer agentId);

    int insert(Agent record);

    int insertSelective(Agent record);

    Agent selectByPrimaryKey(Integer agentId);

    int updateByPrimaryKeySelective(Agent record);

    int updateByPrimaryKey(Agent record);
}