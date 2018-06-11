/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.opengroup.tools.string;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import com.opengroup.exception.BaseFrameworkException;

/** 
 * ������ת��Ϊƴ�� 
 * @author Champion.Wong 
 * 
 */
public class PinYinUtil {
    /** 
     * ת��һ���������� 
     *  
     * @param str 
     * @return 
     */
    public static String convertAll(String str, String seprator) {
        List<String> list = convertAllAsList(str);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            if (sb.length() == 0) {
                sb.append(seprator);
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public static String convertAll(String str) {
        List<String> list = convertAllAsList(str);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static List<String> convertAllAsList(String str) {
        char[] chars = str.toCharArray();
        List<String> result = new ArrayList<String>();
        HanyuPinyinOutputFormat outFormat = new HanyuPinyinOutputFormat();
        outFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        outFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        for (int i = 0; i < chars.length; i++) {
            if (java.lang.Character.toString(chars[i]).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(chars[i], outFormat);
                    result.add(pinyins[0]);
                } catch (Exception e) {
                    throw new BaseFrameworkException(e);
                }
            } else
                result.add(String.valueOf(chars[i]));
        }
        return result;
    }
}
