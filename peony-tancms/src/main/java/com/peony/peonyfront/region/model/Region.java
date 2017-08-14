package com.peony.peonyfront.region.model;

import java.util.ArrayList;
import java.util.List;

import com.peony.core.base.pojo.BasePojo;

public class Region extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           regionid;

    private Integer           provinceid;

    private String            regionname;

    private Integer           regionlevel;

    private Integer           parentid;

    private String            regionabbr;

    private Integer           ordinal;

    private String            regionIdArray;

    private List<Region>      list             = new ArrayList<Region>();

    public String getRegionIdArray() {
        return regionIdArray;
    }

    public void setRegionIdArray(String regionIdArray) {
        this.regionIdArray = regionIdArray;
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

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public Integer getRegionlevel() {
        return regionlevel;
    }

    public void setRegionlevel(Integer regionlevel) {
        this.regionlevel = regionlevel;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getRegionabbr() {
        return regionabbr;
    }

    public void setRegionabbr(String regionabbr) {
        this.regionabbr = regionabbr;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public List<Region> getList() {
        return list;
    }

    public void setList(List<Region> list) {
        this.list = list;
    }
}