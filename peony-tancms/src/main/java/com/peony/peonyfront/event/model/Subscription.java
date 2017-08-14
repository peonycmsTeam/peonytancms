package com.peony.peonyfront.event.model;

import com.peony.core.base.pojo.BasePojo;

public class Subscription extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           subscriptionId;

    private String            eventId;

    private String            userId;

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}