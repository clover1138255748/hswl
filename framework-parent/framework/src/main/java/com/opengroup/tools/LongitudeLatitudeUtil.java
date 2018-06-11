package com.opengroup.tools;

/**
 * 地球表面两点距离计算器
 * 
 * @author haiyang.jiang  
 * @version $Id: EarthSurfaceDistanceCalculator.java, v 0.1 2015年7月15日 下午2:58:51 niya Exp $
 */
public class LongitudeLatitudeUtil {

    //地球半径,米
    private static double EARTH_RADIUS = 6371000;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据AB两点的经纬度计算AB两点距离
     * @param lat1  点A的纬度
     * @param lng1  点A的经度
     * @param lat2  点B的纬度
     * @param lng2 点B的经度
     * @return AB两点距离，单位米
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        if (!validateLat(lat1) || !validateLat(lat2) || !validateLng(lng1) || !validateLng(lng2)) {
            return -1; //不能定位经纬度时约定返回-1
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
     * 根据中心点和距离计算 左下角和右上角的经纬度坐标
     * 
     * @param raidus 单位米
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
     * 验证纬度值是否有效
     * 
     * @param lat
     * @return
     */
    public static boolean validateLat(double lat) {
        return lat > 0 && lat < 90;
    }

    /**
     * 验证经度值是否有效
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
     * 得到两点间的距离 米-----汽车商城的用的是这个,计算数据比较准确
     * @param lat1 第一点纬度
     * @param lng1 第一点经度
     * @param lat2 第二点纬度
     * @param lng2 第二点经度
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
     * 地球半径：6378.137KM
     */ 
    private static double EARTH_RADIUS1 = 6378.137; 
}
