package com.peony.peonyfront.agent.service;

import com.peony.peonyfront.agent.model.Agent;

public interface AgentService {
    Agent selectByPrimaryKey(Integer agentId);
}
