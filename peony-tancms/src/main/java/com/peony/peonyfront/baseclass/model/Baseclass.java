package com.peony.peonyfront.baseclass.model;

import java.util.ArrayList;
import java.util.List;

import com.peony.core.base.pojo.BasePojo;

public class Baseclass extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           baseclassId;                                  // 主键id

    private Integer           baseclassPid;                                 // 父类id

    private String            name;                                         // 名称

    private String            order;                                        // 次序

    private String            explain;                                      // 说明

    // 存放主节点下的子节点列表
    private List<Baseclass>   baseclassList    = new ArrayList<Baseclass>();

    public Integer getBaseclassId() {
        return this.baseclassId;
    }

    public void setBaseclassId(Integer baseclassId) {
        this.baseclassId = baseclassId;
    }

    public Integer getBaseclassPid() {
        return this.baseclassPid;
    }

    public void setBaseclassPid(Integer baseclassPid) {
        this.baseclassPid = baseclassPid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<Baseclass> getBaseclassList() {
        return this.baseclassList;
    }

    public void setBaseclassList(List<Baseclass> baseclassList) {
        this.baseclassList = baseclassList;
    }

}