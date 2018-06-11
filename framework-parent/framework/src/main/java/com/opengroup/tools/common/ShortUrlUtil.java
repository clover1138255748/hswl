/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.tools.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 封装短地址生成API工具类 （新浪、百度）
 * @author sandy
 * @version $Id: ShortUrlUtil.java, v 0.1 2016年7月22日 下午2:48:22 sandy Exp $
 */
public class ShortUrlUtil {
    private static String CREATE_URL = "http://dwz.cn/create.php";                                                   //生成短地址API接口
    private static String QUERY_URL  = "http://dwz.cn/query.php";                                                    //还原知地址API接口
    private static String ENCODE_STR = "utf-8";                                                                      //接口参数及返回值字符编码格式

    private static String url_sina   = "http://api.t.sina.com.cn/short_url/shorten.json?source=3271760578&url_long="; //新浪短地址API接口

    /**
     * 新浪接口 生成 短连接地址
     * @param url 长连接地址
     * @return String
     * @throws Exception
     */
    public static String createForSina(String url) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url_sina + url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        String jsonStr = EntityUtils.toString(response.getEntity(), ENCODE_STR);
        JSONArray jSONArray = JSONArray.parseArray(jsonStr);
        JSONObject object = jSONArray.getJSONObject(0);
        return object.getString("url_short");
    }

    /**
     * 生成 短连接地址【暂时公司域名生成不了，原因不明，其它地址没问题】
     * @param url 长连接地址
     * @return String
     * @throws Exception
     */
    @Deprecated
    public static String createForBaidu(String url) throws Exception {
        String jsonStr = ShortUrlUtil.doPost(CREATE_URL, "url", url);
        JSONObject object = JSON.parseObject(jsonStr);
        return object.getString("tinyurl");
    }

    /**
     * 查询原生长连接地址【暂时公司域名生成不了，原因不明，其它地址没问题】
     * @param url 短连接地址
     * @return String
     * @throws Exception
     */
    @Deprecated
    public static String queryForBaidu(String url) throws Exception {
        String jsonStr = ShortUrlUtil.doPost(QUERY_URL, "tinyurl", url);
        JSONObject object = JSON.parseObject(jsonStr);
        return object.getString("longurl");
    }

    /**
     * HttpClient POST请求方法
     * @param url 请求URL
     * @param inKey 入参名
     * @param inVal 入参值
     * @return String 返回JSON字符串
     * @throws Exception
     */
    private static String doPost(String url, String inKey, String inVal) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair(inKey, inVal));
        post.setEntity(new UrlEncodedFormEntity(parameters, ENCODE_STR));
        CloseableHttpResponse response = httpclient.execute(post);
        return EntityUtils.toString(response.getEntity(), ENCODE_STR);
    }

    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        String url = "http://www.redlion56.com/";
        url = ShortUrlUtil.createForSina(url);
        System.out.println(url);
        //        url = ShortUrlUtil.queryForBaidu(url);
        //        System.out.println(url);
    }
}
