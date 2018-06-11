/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.security;

import java.net.URLEncoder;
import java.security.MessageDigest;

import com.opengroup.exception.BaseFrameworkException;

/**
 * MD5加密工具集
 * @author jin
 * @version $Id: Md5.java, v 0.1 2015年7月22日 下午3:03:15 jin Exp $
 */
public class Md5Util {
    /**
     * 32位MD5加密
     * 
     * @param content
     * @return
     */
    public static String parse32(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] resultByte = md.digest(content.getBytes("UTF-8"));
            StringBuffer buf = new StringBuffer("");
            int i;
            for (int offset = 0; offset < resultByte.length; offset++) {
                i = resultByte[offset];
                i = i & 0xff;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            throw new BaseFrameworkException(e, "MD5转化出错");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(parse32("123456"));
            String content = URLEncoder.encode("尊敬的黄小姐:  感谢您注册为物流嘀嘀用户。您的手机验证为 0192863【物流嘀嘀】",
                "UTF-8");
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
