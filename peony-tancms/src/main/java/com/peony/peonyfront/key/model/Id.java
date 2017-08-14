package com.peony.peonyfront.key.model;

import com.peony.core.base.pojo.BasePojo;

public class Id extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = -37230528652509126L;

    private String            idKey;

    private int               idValue;

    public int getIdValue() {
        return idValue;
    }

    public void setIdValue(int idValue) {
        this.idValue = idValue;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

}