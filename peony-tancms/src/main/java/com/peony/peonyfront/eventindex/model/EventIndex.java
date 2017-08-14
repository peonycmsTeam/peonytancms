package com.peony.peonyfront.eventindex.model;

public class EventIndex {
    private String  id;

    private String  title;

    private Integer regionid;

    private String  regionname;

    private String  date;

    private String  month;

    private String  year;

    private Float   mediaattentionindex;

    private Float   netizensattentionindex;

    private Float   compositeindex;

    private String  type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Float getCompositeindex() {
        return compositeindex;
    }

    public Float getMediaattentionindex() {
        return mediaattentionindex;
    }

    public void setMediaattentionindex(Float mediaattentionindex) {
        this.mediaattentionindex = mediaattentionindex;
    }

    public Float getNetizensattentionindex() {
        return netizensattentionindex;
    }

    public void setNetizensattentionindex(Float netizensattentionindex) {
        this.netizensattentionindex = netizensattentionindex;
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