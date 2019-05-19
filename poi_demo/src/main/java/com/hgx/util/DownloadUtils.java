package com.hgx.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DownloadUtils {
    public void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/octet-stream");
//        fileName = response.encodeURL(new String(fileName.getBytes(),"utf-8"));
        fileName = response.encodeURL(new String(fileName.getBytes(),"iso8859-1"));
        //保存的文件名,必须和页面编码一致,否则乱码
        response.addHeader("Content-Disposition","attachment;filename=" + fileName);
        response.setContentLength(byteArrayOutputStream.size());
        response.addHeader("Content-Length", "" + byteArrayOutputStream.size());
        ServletOutputStream outputstream = response.getOutputStream();
        //取得输出流
        byteArrayOutputStream.writeTo(outputstream);
        //写到输出流
        byteArrayOutputStream.close();
        //关闭
        outputstream.flush();
        //刷数据
    }
}
