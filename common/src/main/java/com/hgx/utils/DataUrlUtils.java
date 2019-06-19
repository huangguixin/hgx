package com.hgx.utils;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class DataUrlUtils {

    public static void main(String[] args) {
//        使用DataURL的形式存储图片（对图片byte数组进行base64编码）
        String encode = "data:image/png;base64," + Base64.encode(new byte[1024]);
    }

}
