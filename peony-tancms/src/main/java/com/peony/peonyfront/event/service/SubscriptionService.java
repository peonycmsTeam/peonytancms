package com.peony.peonyfront.event.service;

import com.peony.peonyfront.event.model.Subscription;

public interface SubscriptionService {

    public Subscription findSubscriptionByUserId(String userId);

    public int deleteSubscriptionByUserId(String userId);

    public int insert(Subscription subscription);
}
