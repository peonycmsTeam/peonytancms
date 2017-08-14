package com.peony.peonyfront.subject.model;

import com.peony.core.base.pojo.BasePojo;

/**
 * 信息来源
 * 
 * @author vv
 *
 */
public class WebDictionary extends BasePojo {

    private static final long serialVersionUID = 1L;

    private int               id;

    private String            name;                 // 显示名称

    private String            value;                // 数据库匹配数据

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
