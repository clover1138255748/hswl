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
 * ��װ�̵�ַ����API������ �����ˡ��ٶȣ�
 * @author sandy
 * @version $Id: ShortUrlUtil.java, v 0.1 2016��7��22�� ����2:48:22 sandy Exp $
 */
public class ShortUrlUtil {
    private static String CREATE_URL = "http://dwz.cn/create.php";                                                   //���ɶ̵�ַAPI�ӿ�
    private static String QUERY_URL  = "http://dwz.cn/query.php";                                                    //��ԭ֪��ַAPI�ӿ�
    private static String ENCODE_STR = "utf-8";                                                                      //�ӿڲ���������ֵ�ַ������ʽ

    private static String url_sina   = "http://api.t.sina.com.cn/short_url/shorten.json?source=3271760578&url_long="; //���˶̵�ַAPI�ӿ�

    /**
     * ���˽ӿ� ���� �����ӵ�ַ
     * @param url �����ӵ�ַ
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
     * ���� �����ӵ�ַ����ʱ��˾�������ɲ��ˣ�ԭ������������ַû���⡿
     * @param url �����ӵ�ַ
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
     * ��ѯԭ�������ӵ�ַ����ʱ��˾�������ɲ��ˣ�ԭ������������ַû���⡿
     * @param url �����ӵ�ַ
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
     * HttpClient POST���󷽷�
     * @param url ����URL
     * @param inKey �����
     * @param inVal ���ֵ
     * @return String ����JSON�ַ���
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
