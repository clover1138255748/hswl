/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tool.securityUtil;

import junit.framework.Assert;

import org.junit.Test;

import com.opengroup.tools.security.AesUtil;

/**
 * 
 * @author jin
 * @version $Id: AesUtilTest.java, v 0.1 2015年8月19日 下午7:29:49 jin Exp $
 */
public class AesUtilTest {
    @Test
    public void testAesBase64() throws Exception {
        String content = "12.00,122.99,4545,浙ABCD12,sdf,222.00";
        String base64AfterEncrypt = AesUtil.encryptToBase64(content.getBytes());
        byte[] base64AfterDecrypt = AesUtil.decryptFromBase64(base64AfterEncrypt);
        Assert.assertEquals(content, new String(base64AfterDecrypt));
    }

    @Test
    public void testAesBytes() throws Exception {
        String content = "cocobabyssssssssssss";
        byte[] afterEncrypt = AesUtil.encrypt(content.getBytes("utf-8"));

        byte[] afterDecrypt = AesUtil.decrypt(afterEncrypt);
        Assert.assertEquals(content, new String(afterDecrypt));
    }

    @Test
    public void testA() throws Exception {
        String c = "nT+1T3jBggkAMWEB6tDIjr78T8xo4jo2i2EIdF8gcIsAIi+EWHzUb8utCNrvo3wk";
        byte[] b = AesUtil.decryptFromBase64(c);
        String s = new String(b);
    }

}
