package com.peony.peonyfront.util.log;

/**
 * 登录或操作 jhj
 */
public enum Type implements EntityEnum {

    LOGIN(1, "LOGIN"), OPERATE(2, "OPERATE"),;

    private int    value;

    private String name;

    Type(int value, String name) {
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
