package com.peony.peonyfront.event.dao;

import com.peony.peonyfront.event.model.Subscription;

public interface SubscriptionMapper {
    int deleteByPrimaryKey(Integer subscriptionId);

    int insert(Subscription record);

    int insertSelective(Subscription record);

    Subscription selectByPrimaryKey(Integer subscriptionId);

    int updateByPrimaryKeySelective(Subscription record);

    int updateByPrimaryKey(Subscription record);

    Subscription selectSubscriptionByUserId(String userId);

    int deleteSubscriptionByUserId(String userId);
}