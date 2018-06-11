package com.opengroup.tools;

/**
 * �������������������
 * 
 * @author haiyang.jiang  
 * @version $Id: EarthSurfaceDistanceCalculator.java, v 0.1 2015��7��15�� ����2:58:51 niya Exp $
 */
public class LongitudeLatitudeUtil {

    //����뾶,��
    private static double EARTH_RADIUS = 6371000;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * ����AB����ľ�γ�ȼ���AB�������
     * @param lat1  ��A��γ��
     * @param lng1  ��A�ľ���
     * @param lat2  ��B��γ��
     * @param lng2 ��B�ľ���
     * @return AB������룬��λ��
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        if (!validateLat(lat1) || !validateLat(lat2) || !validateLng(lng1) || !validateLng(lng2)) {
            return -1; //���ܶ�λ��γ��ʱԼ������-1
        }
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                                           * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    

    /**
     * �������ĵ�;������ ���½Ǻ����Ͻǵľ�γ������
     * 
     * @param raidus ��λ��
     * return minLat,minLng,maxLat,maxLng
    */
    public static double[] getAround(double lat, double lng, int raidus) {
        if (!validateLat(lat) || !validateLng(lng) || raidus < 0) {
            return null;
        }

        Double latitude = lat;
        Double longitude = lng;

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        return new double[] { minLat, minLng, maxLat, maxLng };
    }

    /**
     * ��֤γ��ֵ�Ƿ���Ч
     * 
     * @param lat
     * @return
     */
    public static boolean validateLat(double lat) {
        return lat > 0 && lat < 90;
    }

    /**
     * ��֤����ֵ�Ƿ���Ч
     * 
     * @param lng
     * @return
     */
    public static boolean validateLng(double lng) {
        return lng > 0 && lng < 180;
    }

    public static void main(String[] args) {
        double[] points = getAround(39.916042, 116.403694, 13000);
        System.out.println(points[0] + "," + points[1] + "," + points[2] + "," + points[3]);
    }
    
    
    
    /**
     * �õ������ľ��� ��-----�����̳ǵ��õ������,�������ݱȽ�׼ȷ
     * @param lat1 ��һ��γ��
     * @param lng1 ��һ�㾭��
     * @param lat2 �ڶ���γ��
     * @param lng2 �ڶ��㾭��
     * @return
     */ 
    public static double getDistanceOfMeter(double lat1, double lng1, 
            double lat2, double lng2) { 
        double radLat1 = rad1(lat1); 
        double radLat2 = rad1(lat2); 
        double a = radLat1 - radLat2; 
        double b = rad1(lng1) - rad1(lng2); 
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) 
                + Math.cos(radLat1) * Math.cos(radLat2) 
                * Math.pow(Math.sin(b / 2), 2))); 
        s = s * EARTH_RADIUS1; 
        s = Math.round(s * 10000) / 10; 
        return s; 
    } 
       
    private static double rad1(double d) { 
        return d * Math.PI / 180.0; 
    } 
   
    /**
     * ����뾶��6378.137KM
     */ 
    private static double EARTH_RADIUS1 = 6378.137; 
}
