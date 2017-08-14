package com.peony.peonyfront.report.model;

/**
 * 日报配置表
 * 
 * @author jhj
 *
 */
public class ReportConfig {

    private Integer reportConfigId;

    private String  reportTempId;

    private String  receiveModel;

    private Integer userId;

    private String  createTime;

    private String  reportCreate;  // 是否生成
    private String  reportSend;    // 是否发送

    public String getReportCreate() {
        return reportCreate;
    }

    public void setReportCreate(String reportCreate) {
        this.reportCreate = reportCreate;
    }

    public String getReportSend() {
        return reportSend;
    }

    public void setReportSend(String reportSend) {
        this.reportSend = reportSend;
    }

    public Integer getReportConfigId() {
        return reportConfigId;
    }

    public void setReportConfigId(Integer reportConfigId) {
        this.reportConfigId = reportConfigId;
    }

    public String getReportTempId() {
        return reportTempId;
    }

    public void setReportTempId(String reportTempId) {
        this.reportTempId = reportTempId;
    }

    public String getReceiveModel() {
        return receiveModel;
    }

    public void setReceiveModel(String receiveModel) {
        this.receiveModel = receiveModel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}