package com.peony.peonyfront.event.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.event.dao.SubscriptionMapper;
import com.peony.peonyfront.event.model.Subscription;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Resource
    private SubscriptionMapper subscriptionMapper;

    @Override
    public Subscription findSubscriptionByUserId(String userId) {
        return this.subscriptionMapper.selectSubscriptionByUserId(userId);
    }

    @Override
    public int deleteSubscriptionByUserId(String userId) {
        return this.subscriptionMapper.deleteSubscriptionByUserId(userId);
    }

    @Override
    public int insert(Subscription subscription) {
        return this.subscriptionMapper.insert(subscription);
    }

}
