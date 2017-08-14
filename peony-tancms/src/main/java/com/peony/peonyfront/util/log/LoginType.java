package com.peony.peonyfront.util.log;

/**
 * 登录方式 jhj
 */
public enum LoginType implements EntityEnum {

    PC(1, "PC"), mobile(2, "mobile"),;

    private int    value;

    private String name;

    LoginType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
