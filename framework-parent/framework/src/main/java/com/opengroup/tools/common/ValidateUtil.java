package com.opengroup.tools.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��֤������
 * 
 * @author binyao.ruan
 * @version $Id: ValidateUtil.java, v 0.1 2015��7��14�� ����10:19:42 binyao.ruan Exp $
 */
public class ValidateUtil {

    /**
     * ��֤�Ƿ����ֻ�����
     * 
     * @param cellphone
     * @return
     */
    public static boolean isCellphone(String cellphone) {
        boolean flag = false;
        try {
            Pattern pat = Pattern.compile("^1[2|3|4|5|6|7|8|9]{1}[0-9]{9}$");
            flag = pat.matcher(cellphone).matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * ��֤�Ƿ����������
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        boolean flag = false;
        try {
            String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern pat = Pattern.compile(pattern1);
            flag = pat.matcher(email).matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * ��֤�Ƿ������֤����
     * 
     * @param identityCode
     * @return
     */
    public static boolean isIdentityCard(String identityCode) {
        boolean flag = false;
        Pattern p15 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
        Pattern p18 = Pattern
            .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        if (p15.matcher(identityCode).matches() || p18.matcher(identityCode).matches()) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * ��֤�Ƿ��ǳ��ƺ���
     * 
     * @param truckNumber
     * @return
     */
    public static boolean isTruckNumber(String truckNumber) {
        boolean flag = false;
        try {
            String pattern1 = "^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";
            Pattern pat = Pattern.compile(pattern1);
            flag = pat.matcher(truckNumber).matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * ��֤�Ƿ���ũ�ó����ƺ���
     * 
     * @param truckNumber
     * @return
     */
    public static boolean isFarmTruckNumber(String truckNumber) {
        boolean flag = false;
        try {
            String pattern1 = "^[\u4e00-\u9fa5]{1}[0-9]{2}[A-Z_0-9]{5}$";
            Pattern pat = Pattern.compile(pattern1);
            flag = pat.matcher(truckNumber).matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * ��֤�ǲ�����������
     *
     * @param telephone
     * @return
     */
    public static boolean isTelephone(String telephone) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile("([0-9]{3,4}-)?[0-9]{7,8}");
            Matcher matcher = pattern.matcher(telephone);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
