package com.peony.peonyfront.eventnewsregion.model;

import com.peony.core.base.pojo.BasePojo;

public class EventnewsRegion extends BasePojo {
    /**
     *
     */
    private static final long serialVersionUID = 241373874951701917L;

    private String id;

    private String eventnewsid;

    private Integer regionid;

    private Integer provinceid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventnewsid() {
        return eventnewsid;
    }

    public void setEventnewsid(String eventnewsid) {
        this.eventnewsid = eventnewsid;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }
}