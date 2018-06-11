/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tool.kv;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opengroup.BaseSpringTest;
import com.opengroup.tools.kv.KvClient;
import com.opengroup.tools.random.UniqueIdGenerator;

/**
 * kv类的测试
 * @author jin
 * @version $Id: KvTest.java, v 0.1 2015年8月12日 下午8:23:09 jin Exp $
 */
public class KvTest extends BaseSpringTest {
    @Autowired
    private KvClient kvClient;

    @Test
    public void redisSaveTest() {
        String key = UniqueIdGenerator.generateRandomStr(10);
        String value = UniqueIdGenerator.generateRandomStr(10);
        kvClient.save(key, value, 60);
    }

    @Test
    public void redisReadTest() {
        String key = UniqueIdGenerator.generateRandomStr(10);
        String value = UniqueIdGenerator.generateRandomStr(10);
        kvClient.save(key, value, 60);
        String s = kvClient.read(key, String.class);
        Assert.assertEquals(value, s);
    }

    @Test
    public void redisDelTest() {
        String key = UniqueIdGenerator.generateRandomStr(10);
        String value = UniqueIdGenerator.generateRandomStr(10);
        kvClient.save(key, value, 60);
        kvClient.delete(key);
        String s = kvClient.read(key, String.class);
        Assert.assertNull(s);
    }

    @Test
    public void redisExpiredTest() {
        String key = UniqueIdGenerator.generateRandomStr(10);
        String value = UniqueIdGenerator.generateRandomStr(10);
        kvClient.save(key, value, 1);
        try {
            Thread.sleep(1001);
        } catch (InterruptedException e) {
        }
        String s = kvClient.read(key, String.class);
        Assert.assertNull(s);
    }

}
