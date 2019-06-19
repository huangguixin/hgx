package com.hgx.utils;

import cn.hutool.http.HttpUtil;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
public class IpUtils {

    public static void main(String[] args) {
        //淘宝IP地址库
        String result1 = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + "119.129.112.152");
        System.out.println(result1);

//        {"code":0,"data":{"ip":"119.129.112.152","country":
//            "中国","area":"","region":"广东","city":"广州",
//                    "county":"XX","isp":"电信","country_id":"CN",
//                    "area_id":"","region_id":"440000","city_id":"" +
//                    "440100","county_id":"xx","isp_id":"100017"}}
    }

}
