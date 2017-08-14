package com.peony.peonyfront.industryindex.model;

public class IndustryIndex {
    private String  id;

    private Integer eventid;

    private String  eventname;

    private Integer regionid;

    private String  regionname;

    private String  date;

    private String  month;

    private String  year;

    private Float   heatindex;

    private Float   sensitiveindex;

    private Float   focusindex;

    private Float   compositeindex;

    private String  type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Float getHeatindex() {
        return heatindex;
    }

    public void setHeatindex(Float heatindex) {
        this.heatindex = heatindex;
    }

    public Float getSensitiveindex() {
        return sensitiveindex;
    }

    public void setSensitiveindex(Float sensitiveindex) {
        this.sensitiveindex = sensitiveindex;
    }

    public Float getFocusindex() {
        return focusindex;
    }

    public void setFocusindex(Float focusindex) {
        this.focusindex = focusindex;
    }

    public Float getCompositeindex() {
        return compositeindex;
    }

    public void setCompositeindex(Float compositeindex) {
        this.compositeindex = compositeindex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}