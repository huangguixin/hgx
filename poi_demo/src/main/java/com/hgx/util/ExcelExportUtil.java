package com.hgx.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelExportUtil<T> {

    private int rowIndex;
    private int styleIndex;
    private String templatePath;
    private Class clazz;
    private Field fields[];

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getStyleIndex() {
        return styleIndex;
    }

    public void setStyleIndex(int styleIndex) {
        this.styleIndex = styleIndex;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public ExcelExportUtil(Class clazz, int rowIndex, int styleIndex) {
        this.clazz = clazz;
        this.rowIndex = rowIndex;
        this.styleIndex = styleIndex;
        fields = clazz.getDeclaredFields();
    }

    public ExcelExportUtil(Class clazz, int rowIndex) {
        this.clazz = clazz;
        this.rowIndex = rowIndex;
        fields = clazz.getDeclaredFields();
    }
    /**
     * 基于注解导出
     */
    public void export(HttpServletResponse response,String [] titles, List<T> objs, String fileName) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row firstRow = sheet.createRow(0);

        int titleIndex=0;
        for (String title : titles) {
            Cell cell = firstRow.createCell(titleIndex++);
            cell.setCellValue(title);
        }

        AtomicInteger datasAi = new AtomicInteger(rowIndex);
        for (T t : objs) {
            Row row = sheet.createRow(datasAi.getAndIncrement());
            for (int i = 0; i < firstRow.getLastCellNum(); i++) {
                Cell cell = row.createCell(i);
                for (Field field : fields) {
                    if (field.isAnnotationPresent(ExcelAttribute.class)) {
                        field.setAccessible(true);
                        ExcelAttribute ea = field.getAnnotation(ExcelAttribute.class);
                        if (i == ea.sort()) {
                            cell.setCellValue(field.get(t).toString());
                        }
                    }
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        new DownloadUtils().download(os, response, fileName);
    }

    /**
     * 基于模板加注解导出
     *
     * @param response
     * @param is
     * @param objs
     * @param fileName
     * @throws Exception
     */
    public void exportTemplate(HttpServletResponse response, InputStream is, List<T> objs, String fileName) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        CellStyle[] styles = getTemplateStyles(sheet.getRow(styleIndex));

        AtomicInteger datasAi = new AtomicInteger(rowIndex);
        for (T t : objs) {
            Row row = sheet.createRow(datasAi.getAndIncrement());
            for (int i = 0; i < styles.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(styles[i]);
                for (Field field : fields) {
                    if (field.isAnnotationPresent(ExcelAttribute.class)) {
                        field.setAccessible(true);
                        ExcelAttribute ea = field.getAnnotation(ExcelAttribute.class);
                        if (i == ea.sort()) {
                            cell.setCellValue(field.get(t).toString());
                        }
                    }
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        new DownloadUtils().download(os, response, fileName);
    }

    public CellStyle[] getTemplateStyles(Row row) {
        CellStyle[] styles = new CellStyle[row.getLastCellNum()];
        for (int i = 0; i < row.getLastCellNum(); i++) {
            styles[i] = row.getCell(i).getCellStyle();
        }
        return styles;
    }
}
