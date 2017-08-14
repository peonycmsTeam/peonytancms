package com.peony.peonyfront.warningkeyws.model;

import com.peony.core.base.pojo.BasePojo;

public class Warningkeyws extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           warningkeywsId;

    private Integer           userId;

    private String            name;

    private String            area;

    private String            mainKeyws;

    private String            deputyKeyws;

    private String            ruleoutKeyws;

    public Integer getWarningkeywsId() {
        return warningkeywsId;
    }

    public void setWarningkeywsId(Integer warningkeywsId) {
        this.warningkeywsId = warningkeywsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMainKeyws() {
        return mainKeyws;
    }

    public void setMainKeyws(String mainKeyws) {
        this.mainKeyws = mainKeyws;
    }

    public String getDeputyKeyws() {
        return deputyKeyws;
    }

    public void setDeputyKeyws(String deputyKeyws) {
        this.deputyKeyws = deputyKeyws;
    }

    public String getRuleoutKeyws() {
        return ruleoutKeyws;
    }

    public void setRuleoutKeyws(String ruleoutKeyws) {
        this.ruleoutKeyws = ruleoutKeyws;
    }
}