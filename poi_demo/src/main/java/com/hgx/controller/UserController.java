package com.hgx.controller;

import com.hgx.entity.User;
import com.hgx.util.DownloadUtils;
import com.hgx.util.ExcelExportUtil;
import com.hgx.util.ExcelImportUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    /**
     * 普通导出
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/export")
    public void exportExecl(HttpServletResponse response) throws Exception {
        //1.获取报表数据
        List<User> users = Arrays.asList(new User(01L, "hgx01"), new User(02L, "hgx02"));
        //2.构造Excel
        //创建工作簿
        //SXSSFWorkbook : 百万数据报表
        //Workbook wb = new XSSFWorkbook();
        SXSSFWorkbook wb = new SXSSFWorkbook(100); //阈值，内存中的对象数量最大数量
        //构造sheet
        Sheet sheet = wb.createSheet();
        //创建行
        //标题
        String[] titles = "编号,姓名".split(",");
        //处理标题

        Row row = sheet.createRow(0);

        int titleIndex = 0;
        for (String title : titles) {
            Cell cell = row.createCell(titleIndex++);
            cell.setCellValue(title);
        }

        int rowIndex = 1;
        Cell cell = null;
        for (int i = 0; i < 1; i++) {
            for (User user : users) {
                row = sheet.createRow(rowIndex++);
                // 编号,
                cell = row.createCell(0);
                cell.setCellValue(user.getId());
                // 姓名,
                cell = row.createCell(1);
                cell.setCellValue(user.getName());
            }
        }
        //3.完成下载
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        new DownloadUtils().download(os, response, "用户.xlsx");
    }

    /**
     * 基于模板导出
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/export2")
    public void exportExecl2(HttpServletResponse response) throws Exception {
        List<User> users = Arrays.asList(new User(01L, "hgx01"), new User(02L, "hgx02"));
        Resource resource = new ClassPathResource("excelTemplate/hr-demo.xlsx");
        FileInputStream fis = new FileInputStream(resource.getFile());
        //3.根据模板创建工作簿
        Workbook wb = new XSSFWorkbook(fis);
        //4.读取工作表
        Sheet sheet = wb.getSheetAt(0);

        Row ro = sheet.getRow(0);
        Cell cel = ro.getCell(0);
        cel.setCellValue("用户数据");

        //5.抽取公共样式
        Row row = sheet.getRow(2);
        CellStyle styles[] = new CellStyle[row.getLastCellNum()];
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            styles[i] = cell.getCellStyle();
        }
        //6.构造单元格
        int rowIndex = 2;
        Cell cell = null;
        for (int i = 0; i < 1; i++) {
            for (User user : users) {
                row = sheet.createRow(rowIndex++);
                // 编号,
                cell = row.createCell(0);
                cell.setCellValue(user.getId());
                cell.setCellStyle(styles[0]);
                // 姓名,
                cell = row.createCell(1);
                cell.setCellValue(user.getName());
                cell.setCellStyle(styles[1]);
            }
        }
        //7.下载
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        new DownloadUtils().download(os, response, "用户模板.xlsx");
    }

    @GetMapping("/export3")
    public void exportExecl3(HttpServletResponse response) throws Exception {
        //1.获取报表数据
        List<User> users = Arrays.asList(new User(01L, "hgx01"), new User(02L, "hgx02"));

        Resource resource = new ClassPathResource("excelTemplate/hr-demo.xlsx");
        FileInputStream fis = new FileInputStream(resource.getFile());
        new ExcelExportUtil(User.class, 2, 2).
                exportTemplate(response, fis, users, "人事报表.xlsx");
    }

    @GetMapping("/export4")
    public void exportExecl4(HttpServletResponse response) throws Exception {
        //1.获取报表数据
        List<User> users = Arrays.asList(new User(01L, "hgx01"), new User(02L, "hgx02"));
        //标题
        String[] titles = "编号,姓名".split(",");
        //处理标题
        new ExcelExportUtil(User.class, 1).
                export(response, titles, users, "人事报表.xlsx");
    }

    @PostMapping("import01")
    public void import01(@RequestParam(name = "file") MultipartFile file) throws Exception {
        //1.解析Excel
        //1.1.根据Excel文件创建工作簿
        Workbook wb = new XSSFWorkbook(file.getInputStream());
        //1.2.获取Sheet
        Sheet sheet = wb.getSheetAt(0);//参数：索引
        //1.3.获取Sheet中的每一行，和每一个单元格
        //2.获取用户数据列表
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);//根据索引获取每一个行
            Object[] values = new Object[row.getLastCellNum()];
            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                Cell cell = row.getCell(cellNum);
                Object value = getCellValue(cell);
                System.out.println(value + " ");
            }
            System.out.println();
        }
    }

    @PostMapping("import02")
    public void import02(@RequestParam(name = "file") MultipartFile file) throws IOException {
        ExcelImportUtil<User> importUtil = new ExcelImportUtil<User>(User.class);
        importUtil.readExcel(file.getInputStream(), 1, 0);
    }

    public static Object getCellValue(Cell cell) {
        //1.获取到单元格的属性类型
        CellType cellType = cell.getCellType();
        //2.根据单元格数据类型获取数据
        Object value = null;
        switch (cellType) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    //日期格式
                    value = cell.getDateCellValue();
                } else {
                    //数字
                    value = cell.getNumericCellValue();
                }
                break;
            case FORMULA: //公式
                value = cell.getCellFormula();
                break;
            default:
                break;
        }
        return value;
    }
}
