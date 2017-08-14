package com.peony.peonyfront.event.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.nestframework.commons.utils.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.peony.peonyfront.util.export.AssignedCell;
import com.peony.peonyfront.util.export.AssignedSheet;
import com.peony.peonyfront.util.export.ExportExcelParameter;
import com.peony.peonyfront.util.export.ManagerException;

/**
 * @author wanghai
 * 
 */
@Service
public class ExportSupportServiceImpl implements ExportSupportService {

    /**
     * 导出数据，支持多个sheet的导出文件
     * 
     * @param para
     * @param os
     * @param session
     * @param sheetMap
     */
    public void export(ExportExcelParameter para, OutputStream os, HttpSession session) {
        String templateFilePath = null;
        FileInputStream fis = null;
        try {
            // 读模版文件
            // 模版文件的绝对路径
            templateFilePath = getAbsolutePath(session.getServletContext(), para.getTemplateName());

            fis = new FileInputStream(new File(templateFilePath));
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            List<String> sheetNames = new ArrayList<String>();

            // 记录当前sheet和要追加到的sheet
            Map<String, List<String>> mergedSheets = new HashMap<String, List<String>>();

            // 创建sheet，并复制模板页中的所有行
            for (AssignedSheet aSheet : para.getSheets()) {
                //
                HSSFSheet sheet = null;
                if (aSheet.getTemplateSheetName().equals(aSheet.getSheetName())) {
                    // 导出SheetName和模板Sheet名一致
                    sheet = wb.getSheet(aSheet.getTemplateSheetName());
                    sheetNames.add(aSheet.getSheetName());
                } else {
                    sheet = wb.cloneSheet(wb.getSheetIndex(aSheet.getTemplateSheetName()));
                    wb.setSheetName(wb.getNumberOfSheets() - 1, aSheet.getSheetName());
                    sheetNames.add(wb.getSheetName(wb.getNumberOfSheets() - 1));
                }

                // 开始处理
                int rowNumber = aSheet.getDataRow().getRow();
                HSSFRow datarow = sheet.getRow(rowNumber);
                HSSFRow hlDataRow = null;
                int hldatacol = 0;
                if (aSheet.getHighLightRow() != null) {
                    hlDataRow = sheet.getRow(aSheet.getHighLightRow().getRow());
                    hldatacol = aSheet.getHighLightRow().getCol();
                } else {
                    hlDataRow = datarow;
                }
                if (hlDataRow == null)
                    hlDataRow = sheet.getRow(rowNumber);
                // 输出数据
                List<AssignedCell[]> data = aSheet.getData();

                // 输出数据
                outputData(wb, sheet, datarow, hlDataRow, aSheet.getDataRow(), data, aSheet.getAssignedCells(), aSheet.isNeedCopyTemplateRow(), aSheet.getDataRowSpan(), aSheet.getTotalCol(), hldatacol);

                // 处理合并sheet的记录
                if (StringUtil.isEmpty(aSheet.getAppendToSheet()) || !mergedSheets.containsKey(aSheet.getAppendToSheet())) {
                    // 如果该sheet没有指定追加目标，则该sheet作为一个被保留的sheet，建立一个空列表
                    List<String> list = new ArrayList<String>();
                    list.add(wb.getSheetName(wb.getNumberOfSheets() - 1));
                    mergedSheets.put(aSheet.getSheetName(), list);
                } else {
                    // 如果该sheet指定了要追加到某个sheet上，则先取出目标sheet的列表，并追加到sheet中
                    // 先检查目标sheet是否存在
                    if (mergedSheets.containsKey(aSheet.getAppendToSheet())) {
                        List<String> list = mergedSheets.get(aSheet.getAppendToSheet());
                        list.add(wb.getSheetName(wb.getNumberOfSheets() - 1));
                    }
                }

            }

            // 合并sheet
            for (String sn : mergedSheets.keySet()) {
                List<String> slist = mergedSheets.get(sn);
                // 列表中第一个为目标sheet，
                HSSFSheet targetSheet = wb.getSheet(slist.get(0));
                // 其他为sourceSheet
                for (int i = 1; i < slist.size(); i++) {
                    HSSFSheet sourceSheet = wb.getSheet(slist.get(i));
                    copySheet(sourceSheet, targetSheet);
                    adjustformula(targetSheet);
                    wb.removeSheetAt(wb.getSheetIndex(slist.get(i)));
                }
            }

            // 删除文件中除了有所需数据的sheet页外的所有sheet，包括模板sheet和空白sheet
            int i = 0;
            while (i < wb.getNumberOfSheets()) {
                String sheetName = wb.getSheetName(i);
                if (sheetNames.contains(sheetName)) {
                    i++;
                    continue;
                } else {
                    wb.removeSheetAt(i);
                }
            }

            wb.write(os);
        } catch (Exception e) {
            throw new ManagerException("导出出错。", e);
        }
    }

    /**
     * 单sheet页的导出处理
     */
    public void export(ExportExcelParameter para, OutputStream os, HttpSession session, List<AssignedCell[]> data) {
        String templateFilePath = null;
        FileInputStream fis = null;
        try {
            // 读模版文件
            // 模版文件的绝对路径
            templateFilePath = getAbsolutePath(session.getServletContext(), para.getTemplateName());
            fis = new FileInputStream(new File(templateFilePath));
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);
            // 开始处理
            HSSFRow datarow = sheet.getRow(para.getDataRow().getRow());

            HSSFRow hldatarow = null;
            int hldatacol = 0;
            if (para.getHighLightRow() != null) {
                hldatarow = sheet.getRow(para.getHighLightRow().getRow());
                hldatacol = para.getHighLightRow().getCol();
            } else {
                hldatarow = datarow;
            }

            // 输出数据
            outputData(wb, sheet, datarow, hldatarow, para.getDataRow(), data, para.getAssignedCells(), para.isNeedCopyTemplateRow(), para.getDataRowSpan(), para.getTotalCol(), hldatacol);

            // 单页模式不处理合并sheet操作
            wb.write(os);
        } catch (Exception e) {
            throw new ManagerException("导出出错。", e);
        }
    }

    /**
     * 输出数据
     * 
     * @param wb
     * @param sheet
     * @param datarow
     * @param hldatarow
     * @param dataRow
     * @param data
     * @param assignedCells
     * @param isNeedCopyTemplateRow
     * @param dataRowSpan
     * @param totalCol
     * @param hldatacol
     */
    private void outputData(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow datarow, HSSFRow hldatarow, AssignedCell dataRow, List<AssignedCell[]> data, List<AssignedCell> assignedCells, boolean isNeedCopyTemplateRow, int dataRowSpan, int totalCol, int hldatacol) {
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        int rowNumber = 0;
        int rowNum = 0;
        if (datarow != null)
            rowNumber = datarow.getRowNum();
        else
            rowNumber = dataRow.getRow();
        rowNum = rowNumber;

        HSSFRow currRow = null;

        // 输出数据
        for (AssignedCell[] rowData : data) {
            // 如果是用复制行的模式，则调用copyRows复制出需要的内容行，否则，创建新行，并初始化每列数据
            if (isNeedCopyTemplateRow) {
                copyRows(sheet, rowNum, rowNum + dataRowSpan - 1, rowNumber, totalCol);
            } else {
                // 创建多行，把所有列都创建出来，并使用样式处理
                for (int i = 0; i < dataRowSpan; i++) {
                    currRow = sheet.createRow(rowNumber + i);
                    // 设置行高
                    if (datarow != null)
                        currRow.setHeight(datarow.getHeight());
                    // 创建所有的列
                    for (int j = 0; j < totalCol; j++) {
                        HSSFCell cell = currRow.createCell(j);
                        if (datarow != null)
                            cell.setCellStyle(datarow.getCell(j).getCellStyle());
                    }
                }
            }
            // 根据列总数处理所有列
            for (int k = 0; k < rowData.length; k++) {
                AssignedCell acell = rowData[k];
                if (acell == null)
                    continue;
                // 对特殊样式的处理

                if (acell.getUseStyle() == 9) {
                    // 写照片
                    // 处理照片
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) acell.getCol(), rowNumber + acell.getRow(), (short) (acell.getColEnd() + 1), rowNumber + acell.getRowEnd() + 1);
                    anchor.setAnchorType(2);
                    // 2008-09-19 修改批量报名的可以没有照片
                    if (StringUtil.isNotEmpty((String) acell.getValue())) {
                        if (((String) acell.getValue()).startsWith("http")) {
                            try {
                                patriarch.createPicture(anchor, loadPicture(new URL((String) acell.getValue()), wb));
                            } catch (MalformedURLException e) {
                            }
                        } else {
                            patriarch.createPicture(anchor, loadPicture((String) acell.getValue(), wb));
                        }
                    }
                    continue;
                }

                // 根据属性合并单元格
                if (acell.getRow() != acell.getRowEnd() || acell.getCol() != acell.getColEnd())
                    sheet.addMergedRegion(new CellRangeAddress(rowNumber + acell.getRow(), rowNumber + acell.getRowEnd(), acell.getCol(), acell.getColEnd()));

                HSSFRow row = sheet.getRow(rowNumber + acell.getRow());
                if (row == null)
                    row = sheet.createRow(rowNumber + acell.getRow());
                HSSFCell cell = row.getCell(acell.getCol());
                if (cell == null)
                    cell = row.createCell(acell.getCol());
                // 根据类型设置
                int cType = HSSFCell.CELL_TYPE_STRING;

                Object value = acell.getValue();
                if (acell.getUseStyle() == 8) {
                    // 公式,根据当前行解析公式,如R[-2]C/R[-1]C
                    // Pattern p
                    cell.setCellFormula(cell.getCellFormula());
                    continue;
                }
                if (value == null) {
                    cell.setCellValue("");
                } else {
                    if (value instanceof Integer || value instanceof Double) {
                        cType = HSSFCell.CELL_TYPE_NUMERIC;
                        try {
                            cell.setCellValue(new BigDecimal(value.toString()).doubleValue());
                        } catch (Exception e) {
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellValue(value.toString());
                        }
                    } else {
                        cType = HSSFCell.CELL_TYPE_STRING;
                        cell.setCellType(cType);
                        cell.setCellValue(value.toString());
                    }
                }
                // 使用样式
                if (acell.getUseStyle() == 0) {
                    // 使用默认样式
                    if (datarow != null)
                        cell.setCellStyle(datarow.getCell(acell.getCol()).getCellStyle());
                } else if (acell.getUseStyle() == 1) {
                    // 使用数据样式
                    cell.setCellStyle(hldatarow.getCell(hldatacol).getCellStyle());
                } else if (acell.getUseStyle() == 2) {
                    // 使用高亮样式
                    cell.setCellStyle(hldatarow.getCell(acell.getCol()).getCellStyle());
                }
            }
            rowNumber += dataRowSpan;
        }
        // 输出指定位置的值
        if (assignedCells != null && assignedCells.size() > 0) {
            for (AssignedCell cell : assignedCells) {
                if (cell.getValue() == null)
                    continue;
                if (cell.getRow() > sheet.getLastRowNum()) {
                    currRow = sheet.createRow(cell.getRow());
                    for (int j = 0; j < totalCol; j++) {
                        currRow.createCell(j);
                    }
                }
                if (sheet.getRow(cell.getRow()) == null) {
                    sheet.createRow(cell.getRow());
                }
                HSSFCell assignCell = sheet.getRow(cell.getRow()).getCell(cell.getCol());
                if (assignCell == null) {
                    assignCell = sheet.getRow(cell.getRow()).createCell(cell.getCol());
                }
                assignCell.setCellValue(cell.getValue().toString());
            }
        }
        // 重新计算公式
        adjustformula(sheet);
    }

    /**
     * 处理照片
     * 
     * @param filePath
     * @param wb
     * @return
     */
    private static int loadPicture(URL filePath, HSSFWorkbook wb) {
        int result = 0;
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            java.awt.image.BufferedImage bufferImg = ImageIO.read(filePath);
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            result = wb.addPicture(byteArrayOut.toByteArray(), org.apache.poi.hssf.usermodel.HSSFWorkbook.PICTURE_TYPE_JPEG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 处理照片
     * 
     * @param filePath
     * @param wb
     * @return
     */
    private static int loadPicture(String filePath, HSSFWorkbook wb) {
        int result = 0;
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            File url = new File(filePath);
            java.awt.image.BufferedImage bufferImg = ImageIO.read(url);
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            result = wb.addPicture(byteArrayOut.toByteArray(), org.apache.poi.hssf.usermodel.HSSFWorkbook.PICTURE_TYPE_JPEG);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(filePath);
        }
        return result;
    }

    /**
     * 复制sheet中的行数据
     * 
     * @param sheet
     * @param pStartRow
     * @param pEndRow
     * @param pPosition
     */
    protected void copyRows(HSSFSheet sheet, int pStartRow, int pEndRow, int pPosition, int colTotal) {
        HSSFRow sourceRow = null;
        HSSFRow targetRow = null;
        HSSFCell sourceCell = null;
        HSSFCell targetCell = null;
        CellRangeAddress region = null;

        if ((pStartRow == -1) || (pEndRow == -1)) {
            return;
        }
        if (pStartRow == pPosition)
            return;
        // 拷贝合并的单元格
        int numregions = sheet.getNumMergedRegions();
        for (int i = 0; i < numregions; i++) {
            region = sheet.getMergedRegion(i);
            CellRangeAddress region2 = null;
            if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
                int targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                int targetRowTo = region.getLastRow() - pStartRow + pPosition;
                region2 = new CellRangeAddress(targetRowFrom, targetRowTo, region.getFirstColumn(), region.getLastColumn());
                sheet.addMergedRegion(region2);
            }
        }
        // 拷贝行并填充数据
        for (int i = pStartRow; i <= pEndRow; i++) {
            sourceRow = sheet.getRow(i);
            if (sourceRow == null) {
                continue;
            }
            targetRow = sheet.createRow(i - pStartRow + pPosition);
            targetRow.setHeight(sourceRow.getHeight());
            for (int j = sourceRow.getFirstCellNum(); j < colTotal; j++) {
                sourceCell = sourceRow.getCell(j);
                if (sourceCell == null) {
                    continue;
                }
                targetCell = targetRow.createCell(j);
                targetCell.setCellStyle(sourceCell.getCellStyle());
                int cType = sourceCell.getCellType();
                switch (cType) {
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    // 调整公式
                    int dataRowSpan = pPosition - pStartRow;
                    targetCell.setCellFormula(adjustFormula(sourceCell.getCellFormula(), dataRowSpan));
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    targetCell.setCellValue(sourceCell.getStringCellValue());
                    break;
                }
            }
        }
    }

    /**
     * 调整公式
     * 
     * @param cellFormula
     * @param dataRowSpan
     * @return
     */
    private String adjustFormula(String cellFormula, int dataRowSpan) {
        Pattern p = Pattern.compile("[a-zA-Z]+[\\d]*");
        Pattern p2 = Pattern.compile("[\\d]+");
        String str = cellFormula;
        Matcher m = p.matcher(str);
        Matcher m2;
        while (m.find()) {
            String s = m.group();
            m2 = p2.matcher(s);

            while (m2.find()) {
                String newS = m2.replaceAll(Integer.toString(Integer.parseInt(m2.group()) + dataRowSpan));
                str = str.replaceFirst(m.group(), newS);
            }
        }
        return str;
    }

    /**
     * 将sheet中的所有行复制到目标sheet中，并删除原sheet
     * 
     * @param oriSheet
     * @param descSheet
     */
    protected void copySheet(HSSFSheet sourceSheet, HSSFSheet targetSheet) {
        HSSFRow sourceRow = null;
        HSSFRow targetRow = null;
        HSSFCell sourceCell = null;
        HSSFCell targetCell = null;
        CellRangeAddress region = null;

        // 首先获取sourceSheet的最后一行行数
        int pStartRow = 0;
        int pEndRow = sourceSheet.getLastRowNum();

        // 获取targetSheet的最后一行
        int pPosition = targetSheet.getLastRowNum() + 3;

        // 拷贝合并的单元格
        for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            region = sourceSheet.getMergedRegion(i);
            CellRangeAddress region2 = null;
            if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
                int targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                int targetRowTo = region.getLastRow() - pStartRow + pPosition;
                region2 = new CellRangeAddress(targetRowFrom, targetRowTo, region.getFirstColumn(), region.getLastColumn());
                targetSheet.addMergedRegion(region2);
            }
        }
        // 拷贝行并填充数据
        for (int i = 0; i <= pEndRow; i++) {
            sourceRow = sourceSheet.getRow(i);
            if (sourceRow == null) {
                continue;
            }
            targetRow = targetSheet.createRow(i - pStartRow + pPosition);
            targetRow.setHeight(sourceRow.getHeight());
            for (int j = sourceRow.getFirstCellNum(); j < sourceRow.getPhysicalNumberOfCells(); j++) {
                sourceCell = sourceRow.getCell(j);
                if (sourceCell == null) {
                    continue;
                }
                targetCell = targetRow.createCell(j);
                targetCell.setCellStyle(sourceCell.getCellStyle());
                int cType = sourceCell.getCellType();
                targetCell.setCellType(cType);
                switch (cType) {
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    int offset = targetCell.getRowIndex() - sourceCell.getRowIndex() + 1;
                    targetCell.setCellFormula(adjustFormula(sourceCell.getCellFormula(), offset));
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    targetCell.setCellValue(sourceCell.getStringCellValue());
                    break;
                }
            }
        }
    }

    /**
     * 重新计算公式
     */
    private void adjustformula(HSSFSheet sourceSheet) {
        HSSFRow sourceRow = null;
        HSSFCell sourceCell = null;

        // 首先获取sourceSheet的最后一行行数
        int pEndRow = sourceSheet.getLastRowNum();

        // 拷贝行并填充数据
        for (int i = 0; i <= pEndRow; i++) {
            sourceRow = sourceSheet.getRow(i);
            if (sourceRow == null) {
                continue;
            }
            for (int j = sourceRow.getFirstCellNum(); j < sourceRow.getPhysicalNumberOfCells(); j++) {
                sourceCell = sourceRow.getCell(j);
                if (sourceCell == null) {
                    continue;
                }
                int cType = sourceCell.getCellType();
                switch (cType) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    sourceCell.setCellFormula(sourceCell.getCellFormula());
                    break;
                }
            }
        }

    }

    /**
     * 相对路径转绝对路径
     * 
     * @param sc
     *            ServletContext.
     * @param fileRelativePath
     *            相对于web应用的根目录的文件路径(含文件全名)
     * @return String.
     * @throws IOException
     */
    private String getAbsolutePath(ServletContext sc, String fileRelativePath) throws IOException {
        String ret = "";
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        Resource resource = ctx.getResource(fileRelativePath);
        try {
            ret = resource.getFile().getAbsolutePath();
        } catch (IOException ioe) {
            throw ioe;
        }
        return ret;
    }
}
