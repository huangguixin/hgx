package com.hgx;

import com.hgx.handler.SheetHandler;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;

public class PoiTest {

    private final String diskUrl = "E:\\Program Files\\JetBrains\\IntelliJ IDEA 2018.3\\workspace\\hgx\\poi_demo\\src\\test\\resources\\";

    /**
     * 使用POI创建excel
     */
    @Test
    public void poiTest01() {
        //1.创建工作簿  HSSFWorkbook -- 2003
        Workbook wb = new XSSFWorkbook(); //2007版本
        //2.创建表单sheet
        Sheet sheet = wb.createSheet("test");
        //3.文件流
        FileOutputStream pis = null;
        try {
            pis = new FileOutputStream(diskUrl + "test.xlsx");
            //4.写入文件
            wb.write(pis);
            pis.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 创建单元格写入内容
     */
    @Test
    public void poiTest02() {
        //创建工作簿  HSSFWorkbook -- 2003
        Workbook wb = new XSSFWorkbook(); //2007版本
        //创建表单sheet
        Sheet sheet = wb.createSheet("test");
        //创建行对象  参数：索引（从0开始）
        Row row = sheet.createRow(2);
        //创建单元格对象  参数：索引（从0开始）
        Cell cell = row.createCell(2);
        //向单元格中写入内容
        cell.setCellValue("huangguixin");
        //文件流
        FileOutputStream pis = null;
        try {
            pis = new FileOutputStream(diskUrl + "test.xlsx");
            //写入文件
            wb.write(pis);
            pis.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * 单元格样式处理
     */
    @Test
    public void poiTest03() {
        //创建工作簿  HSSFWorkbook -- 2003
        Workbook wb = new XSSFWorkbook(); //2007版本
        //创建表单sheet
        Sheet sheet = wb.createSheet("test");
        //创建行对象  参数：索引（从0开始）
        Row row = sheet.createRow(2);
        //创建单元格对象  参数：索引（从0开始）
        Cell cell = row.createCell(2);
        //向单元格中写入内容
        cell.setCellValue("huangguixin");

        //样式处理
        //创建样式对象
        CellStyle style = wb.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderBottom(BorderStyle.THIN);//下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        //创建字体对象
        Font font = wb.createFont();
        font.setFontName("华文行楷"); //字体
        font.setFontHeightInPoints((short) 28);//字号
        style.setFont(font);

        //行高和列宽
        row.setHeightInPoints(50);//行高
        //列宽的宽度  字符宽度
        sheet.setColumnWidth(2, 31 * 256);//列宽

        //剧中显示
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //向单元格设置样式
        cell.setCellStyle(style);

        //文件流
        FileOutputStream pis = null;
        try {
            pis = new FileOutputStream(diskUrl + "test.xlsx");
            //写入文件
            wb.write(pis);
            pis.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * 插入图片
     */
    @Test
    public void poiTest04() {
        //创建工作簿  HSSFWorkbook -- 2003
        Workbook wb = new XSSFWorkbook(); //2007版本
        //创建表单sheet
        Sheet sheet = wb.createSheet("test");

        //读取图片流
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(diskUrl + "hgx.jpg");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //转化二进制数组
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(stream);
            stream.read(bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //向POI内存中添加一张图片，返回图片在图片集合中的索引
        int index = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);//参数一：图片的二进制数据，参数二：图片类型
        //绘制图片工具类
        CreationHelper helper = wb.getCreationHelper();
        //创建一个绘图对象
        Drawing<?> patriarch = sheet.createDrawingPatriarch();
        //创建锚点，设置图片坐标
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setRow1(0);
        anchor.setCol1(0);
        //绘制图片
        Picture picture = patriarch.createPicture(anchor, index);//图片位置，图片的索引
        picture.resize();//自适应渲染图片

        //文件流
        FileOutputStream pis = null;
        try {
            pis = new FileOutputStream(diskUrl + "test.xlsx");
            //写入文件
            wb.write(pis);
            pis.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 读取excel并解析
     * sheet.getLastRowNum() : 最后一行的索引
     * row.getLastCellNum() ： 最后一个单元格的号码
     */
    @Test
    public void poiTest05() {
        //1.根据Excel文件创建工作簿
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(diskUrl + "test.xlsx");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //2.获取Sheet
        Sheet sheet = wb.getSheetAt(0);//参数：索引
        //3.获取Sheet中的每一行，和每一个单元格
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);//根据索引获取每一个行
            StringBuilder sb = new StringBuilder();
            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                //根据索引获取每一个单元格
                Cell cell = row.getCell(cellNum);
                //获取每一个单元格的内容
                Object value = getCellValue(cell);
                sb.append(value).append("-");
            }
            System.out.println(sb.toString());
        }
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

    /**
     * 使用事件模型解析百万数据excel报表
     */
    @Test
    public void poiTest06() throws Exception {
        String path = diskUrl +"demo.xlsx";

        //1.根据excel报表获取OPCPackage
        OPCPackage opcPackage = OPCPackage.open(path, PackageAccess.READ);
        //2.创建XSSFReader
        XSSFReader reader = new XSSFReader(opcPackage);
        //3.获取SharedStringTable对象
        SharedStringsTable table = reader.getSharedStringsTable();
        //4.获取styleTable对象
        StylesTable stylesTable = reader.getStylesTable();
        //5.创建Sax的xmlReader对象
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        //6.注册事件处理器
        XSSFSheetXMLHandler xmlHandler = new XSSFSheetXMLHandler(stylesTable,table,new SheetHandler(),false);
        xmlReader.setContentHandler(xmlHandler);
        //7.逐行读取
        XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) reader.getSheetsData();
        while (sheetIterator.hasNext()) {
            InputStream stream = sheetIterator.next(); //每一个sheet的流数据
            InputSource is = new InputSource(stream);
            xmlReader.parse(is);
        }
    }

}
