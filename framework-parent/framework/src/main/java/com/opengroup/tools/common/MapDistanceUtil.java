package com.opengroup.tools.common;

import java.util.HashMap;
import java.util.Map;

public class MapDistanceUtil { 
    
    private static double EARTH_RADIUS = 6378.137; 
   
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
     
    /**
     * ��������λ�õľ�γ�ȣ����������صľ��루��λΪKM��
     * ����ΪString����
     * @param lat1 �û�����
     * @param lng1 �û�γ��
     * @param lat2 �̼Ҿ���
     * @param lng2 �̼�γ��
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);
         
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr.
            substring(0, distanceStr.indexOf("."));
         
        return distanceStr;
    }
     
    /**
     * ��ȡ��ǰ�û�һ���������ڵľ�γ��ֵ
     * ��λ�� return minLat
     * ��С���� minLng
     * ��Сγ�� maxLat
     * ��󾭶� maxLng
     * ���γ�� minLat
     */
    public static Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();
         
        Double latitude = Double.parseDouble(latStr);// ��ֵ������
        Double longitude = Double.parseDouble(lngStr);// ��ֵ��γ��
 
        Double degree = (24901 * 1609) / 360.0; // ��ȡÿ��
        double raidusMile = Double.parseDouble(raidus);
         
        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //��ȡ��С����
        Double minLat = longitude - radiusLng;
        // ��ȡ��󾭶�
        Double maxLat = longitude + radiusLng;
         
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // ��ȡ��Сγ��
        Double minLng = latitude - radiusLat;
        // ��ȡ���γ��
        Double maxLng = latitude + radiusLat;
         
        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");
         
        return map;
    }
     
     
}
