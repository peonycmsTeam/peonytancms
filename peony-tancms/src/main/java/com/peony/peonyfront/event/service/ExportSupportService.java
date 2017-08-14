/**
 * 
 */
package com.peony.peonyfront.event.service;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.peony.peonyfront.util.export.AssignedCell;
import com.peony.peonyfront.util.export.ExportExcelParameter;

/**
 * 导出支持接口.
 * 
 * @author wanghai
 * 
 */
public interface ExportSupportService {
    /**
     * 根据模板生成文件
     * 
     * @param para
     * @param os
     * @param session
     * @param data
     */
    public void export(ExportExcelParameter para, OutputStream os, HttpSession session, List<AssignedCell[]> data);

    /**
     * 导出数据，支持多个sheet的导出文件
     * 
     * @param para
     * @param os
     * @param session
     * @param sheetMap
     */
    public void export(ExportExcelParameter para, OutputStream os, HttpSession session);
}
