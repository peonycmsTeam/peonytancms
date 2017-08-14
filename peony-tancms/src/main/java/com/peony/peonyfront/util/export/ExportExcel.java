package com.peony.peonyfront.util.export;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.peony.peonyfront.event.service.ExportSupportService;

public class ExportExcel {
    /**
     * 根据模板导出文件
     */
    public Object exportExcelByTemplate(String title, String filename, HttpServletResponse res, HttpSession session, List<AssignedCell[]> data, ExportSupportService exportSupportService) {
        try {
            ExportExcelParameter para = new ExportExcelParameter();
            para.setTemplateName("/template/export.xls");
            para.setDataRow(new AssignedCell(2, 0, ""));// 数据开始行
            para.setHighLightRow(new AssignedCell(0, 0, ""));// 特殊显示行
            para.setDataRowSpan(1);// 设置每组数据所占的行数，默认为1行
            para.setTotalCol(6);// 设置所有数据占用的总列数，从1开始；

            // 指定位置输出
            List<AssignedCell> assignedCell = new ArrayList<AssignedCell>();

            assignedCell.add(new AssignedCell(0, 0, title));// 添加标题
            para.setAssignedCells(assignedCell);
            res.setContentType("APPLICATION/ms-excel");
            res.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("gbk"), "iso8859-1"));
            ServletOutputStream os = res.getOutputStream();
            exportSupportService.export(para, os, session, data);
            os.flush();
            os.close();
            return null;
        } catch (Exception e) {
            throw new ManagerException("导出出错。", e);
        }

    }

    /**
     * 根据模板导出文件(定制舆情按时间导出)
     */
    public Object exportExcelByTemplateByTime(String title, String filename, HttpServletResponse res, HttpSession session, List<AssignedCell[]> data, ExportSupportService exportSupportService, ExportExcelParameter para) {
        try {
            // para.setTemplateName("/template/export.xls");
            para.setDataRow(new AssignedCell(2, 0, ""));// 数据开始行
            para.setHighLightRow(new AssignedCell(0, 0, ""));// 特殊显示行
            para.setDataRowSpan(1);// 设置每组数据所占的行数，默认为1行
            para.setTotalCol(6);// 设置所有数据占用的总列数，从1开始；

            // 指定位置输出
            List<AssignedCell> assignedCell = new ArrayList<AssignedCell>();

            assignedCell.add(new AssignedCell(0, 0, title));// 添加标题
            para.setAssignedCells(assignedCell);
            res.setContentType("APPLICATION/ms-excel");
            res.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("gbk"), "iso8859-1"));
            ServletOutputStream os = res.getOutputStream();
            exportSupportService.export(para, os, session, data);
            os.flush();
            os.close();
            return null;
        } catch (Exception e) {
            throw new ManagerException("导出出错。", e);
        }

    }
}
