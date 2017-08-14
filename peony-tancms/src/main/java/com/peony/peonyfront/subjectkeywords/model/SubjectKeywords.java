package com.peony.peonyfront.subjectkeywords.model;

import com.peony.core.base.pojo.BasePojo;

public class SubjectKeywords extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           id;

    private Integer           subjectid;

    private String            name;

    private String            keywords;

    private String            rejectflag;

    private Integer           regionid;

    private Integer           mainbodyid;

    private Integer           eventid;

    private String            main;

    private String            qxx;

    private String            qxx1textarea;

    private String            qxx2textarea;

    private String            glc;

    private String            glc1textarea;

    private String            glc2textarea;

    private String            area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getRejectflag() {
        return rejectflag;
    }

    public void setRejectflag(String rejectflag) {
        this.rejectflag = rejectflag;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public Integer getMainbodyid() {
        return mainbodyid;
    }

    public void setMainbodyid(Integer mainbodyid) {
        this.mainbodyid = mainbodyid;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getQxx() {
        return qxx;
    }

    public void setQxx(String qxx) {
        this.qxx = qxx;
    }

    public String getQxx1textarea() {
        return qxx1textarea;
    }

    public void setQxx1textarea(String qxx1textarea) {
        this.qxx1textarea = qxx1textarea;
    }

    public String getQxx2textarea() {
        return qxx2textarea;
    }

    public void setQxx2textarea(String qxx2textarea) {
        this.qxx2textarea = qxx2textarea;
    }

    public String getGlc() {
        return glc;
    }

    public void setGlc(String glc) {
        this.glc = glc;
    }

    public String getGlc1textarea() {
        return glc1textarea;
    }

    public void setGlc1textarea(String glc1textarea) {
        this.glc1textarea = glc1textarea;
    }

    public String getGlc2textarea() {
        return glc2textarea;
    }

    public void setGlc2textarea(String glc2textarea) {
        this.glc2textarea = glc2textarea;
    }
}