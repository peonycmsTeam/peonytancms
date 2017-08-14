package com.peony.peonyfront.util.export;

import java.util.List;

/**
 * 指定的Sheet页属性
 * 
 * @author wanghai
 *
 */
public class AssignedSheet {
    /**
     * Sheet名称
     */
    private String             sheetName           = "Sheet1";
    /**
     * 使用模板Sheet名称
     */
    private String             templateSheetName   = "Sheet1";

    /**
     * 模板文件的总列数(实际数据)
     */
    private int                totalCol;

    /**
     * 在指定位置显示数据
     */
    private List<AssignedCell> assignedCells;

    /**
     * 数据行，数据区读取该行样式
     */
    private AssignedCell       dataRow;

    /**
     * 特殊显示行，特殊行读取该行样式
     */
    private AssignedCell       highLightRow;
    /**
     * 数据行所占行数，默认为1
     */
    private int                dataRowSpan         = 1;

    /**
     * 新数据按新建或copy模板的方式
     */
    private boolean            needCopyTemplateRow = false;

    /**
     * 追加到Sheet
     */
    private String             AppendToSheet;

    public String getAppendToSheet() {
        return AppendToSheet;
    }

    public void setAppendToSheet(String appendToSheet) {
        AppendToSheet = appendToSheet;
    }

    /**
     * 数据内容
     */
    private List<AssignedCell[]> data;

    public AssignedSheet() {

    }

    public AssignedSheet(String sheetName, String templateSheetName) {
        this.sheetName = sheetName;
        this.templateSheetName = templateSheetName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getTemplateSheetName() {
        return templateSheetName;
    }

    public void setTemplateSheetName(String templateSheetName) {
        this.templateSheetName = templateSheetName;
    }

    public int getTotalCol() {
        return totalCol;
    }

    public void setTotalCol(int totalCol) {
        this.totalCol = totalCol;
    }

    public List<AssignedCell> getAssignedCells() {
        return assignedCells;
    }

    public void setAssignedCells(List<AssignedCell> assignedCells) {
        this.assignedCells = assignedCells;
    }

    public AssignedCell getDataRow() {
        return dataRow;
    }

    public void setDataRow(AssignedCell dataRow) {
        this.dataRow = dataRow;
    }

    public AssignedCell getHighLightRow() {
        return highLightRow;
    }

    public void setHighLightRow(AssignedCell highLightRow) {
        this.highLightRow = highLightRow;
    }

    public int getDataRowSpan() {
        return dataRowSpan;
    }

    public void setDataRowSpan(int dataRowSpan) {
        this.dataRowSpan = dataRowSpan;
    }

    public List<AssignedCell[]> getData() {
        return data;
    }

    public void setData(List<AssignedCell[]> data) {
        this.data = data;
    }

    public boolean isNeedCopyTemplateRow() {
        return needCopyTemplateRow;
    }

    public void setNeedCopyTemplateRow(boolean needCopyTemplateRow) {
        this.needCopyTemplateRow = needCopyTemplateRow;
    }

}