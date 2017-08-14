package com.peony.peonyfront.login.model;

import com.peony.core.base.pojo.BasePojo;

public class PhoneLogin extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           id;

    private Integer           userId;

    private String            macId;

    private String            type;

    private String            bindType;

    private String            dndTime;

    private String            pushSwitch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getDndTime() {
        return dndTime;
    }

    public void setDndTime(String dndTime) {
        this.dndTime = dndTime;
    }

    public String getPushSwitch() {
        return pushSwitch;
    }

    public void setPushSwitch(String pushSwitch) {
        this.pushSwitch = pushSwitch;
    }
}