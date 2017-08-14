package com.peony.peonyfront.focus.model;

import java.util.Date;
import java.util.Map;

import com.peony.core.base.pojo.BasePojo;

public class Focus extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = -1398357059092947976L;

    private String            id;

    private String            name;

    private Integer           regionid;

    private String            region;

    private String            person;

    private String            organization;

    private String            keyword1;

    private String            keyword2;

    private String            keyword3;

    private String            keyword4;

    private String            keyword5;

    private String            keyword6;

    private String            keyword7;

    private String            keyword8;

    private String            keyword9;

    private String            keyword10;

    private Date              startdate;

    private Date              enddate;

    private Integer           deleteflag;

    private Date              producedate;

    private Map               dayMap;                                  // 今日数据

    private Map               countMap;                                // 数据总量

    public Map getDayMap() {
        return dayMap;
    }

    public void setDayMap(Map dayMap) {
        this.dayMap = dayMap;
    }

    public Map getCountMap() {
        return countMap;
    }

    public void setCountMap(Map countMap) {
        this.countMap = countMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public String getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(String keyword6) {
        this.keyword6 = keyword6;
    }

    public String getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(String keyword7) {
        this.keyword7 = keyword7;
    }

    public String getKeyword8() {
        return keyword8;
    }

    public void setKeyword8(String keyword8) {
        this.keyword8 = keyword8;
    }

    public String getKeyword9() {
        return keyword9;
    }

    public void setKeyword9(String keyword9) {
        this.keyword9 = keyword9;
    }

    public String getKeyword10() {
        return keyword10;
    }

    public void setKeyword10(String keyword10) {
        this.keyword10 = keyword10;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public Date getProducedate() {
        return producedate;
    }

    public void setProducedate(Date producedate) {
        this.producedate = producedate;
    }
}