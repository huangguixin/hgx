package com.hgx.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * apache httpclient Http请求服务
 *
 */
@Slf4j
public class HttpClientUtil {

    /**
     * 发送 get请求
     */
    public static String get(String reqUrL) {
        HttpGet httpget = new HttpGet(reqUrL);
        return getResponseResult(httpget);
    }


    /**
     * 拼接参数发送 get请求
     */
    public static String getByParam(String reqUrL, Map<String, String> param) {
        String url = getRqstUrlHttp(reqUrL, param);
        HttpGet httpget = new HttpGet(url);
        return getResponseResult(httpget);
    }


    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static String post(String actionUrl, Map<String, String> params) {
        HttpPost httppost = new HttpPost(actionUrl);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
            formparams.add(new BasicNameValuePair(key, params.get(key)));
        }
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        } catch (Exception e) {
            log.debug("发送 post请求访问本地应用并根据传递参数不同返回不同结果" + e.getMessage());
        }
        httppost.setEntity(uefEntity);
        return getResponseResult(httppost);
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static String postByJson(String actionUrl, Map<String, String> params) {
        String json = JsonUtils.serialize(params);
        HttpPost httppost = new HttpPost(actionUrl);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        httppost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));
        return getResponseResult(httppost);
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static String postByJson(String actionUrl, Map<String, String> params, Map<String, String> header) {
        String json = JsonUtils.serialize(params);
        HttpPost httppost = new HttpPost(actionUrl);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        for (String key : header.keySet()) {
            httppost.setHeader(key, header.get(key));
        }
        httppost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));
        return getResponseResult(httppost);
    }

    /**
     * Get请求获取响应码
     *
     * @param reqUrL
     * @return
     */
    public static int getStatusCode(String reqUrL) {
        HttpGet httpget = new HttpGet(reqUrL);
        int statusCode = 500;
        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpget);
        ) {
            statusCode = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            log.debug("获取响应码失败" + e.getMessage());
        }
        return statusCode;
    }


    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static String post(String actionUrl, String contentType, Map<String, String> params) {
        HttpPost httppost = new HttpPost(actionUrl);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
            formparams.add(new BasicNameValuePair(key, params.get(key)));
        }
        httppost.addHeader("Content-type", contentType);
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        } catch (Exception e) {
            log.debug(""+e.getMessage());
        }
        httppost.setEntity(uefEntity);
        return getResponseResult(httppost);
    }


    /**
     * 拼接参数发送 get请求
     */
    public static String getByParam(String reqUrL, Map<String, String> param, Map<String, String> header) {
        String url = getRqstUrlHttp(reqUrL, param);
        HttpGet httpget = new HttpGet(url);
        for (String key : header.keySet()) {
            httpget.setHeader(key, header.get(key));
        }
        return getResponseResult(httpget);
    }

    /**
     * 拼接get请求的url请求地址
     */
    public static String getRqstUrlHttp(String url, Map<String, String> params) {

        StringBuilder builder = new StringBuilder(url);
        boolean isFirst = true;
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                if (isFirst) {
                    isFirst = false;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(params.get(key));
            }
        }
        return builder.toString();
    }

    /**
     * 获取响应结果
     *
     * @param httpRequestBase
     * @return
     */
    public static String getResponseResult(HttpRequestBase httpRequestBase) {
        String result = null;
        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpRequestBase);
        ) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.debug("httpclient请求失败" + e.getMessage());
        }
        return result;
    }

}
