/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opengroup.framework.testDal.TestTbDAO;
import com.opengroup.framework.testDal.TestTbDO;

/**
 * 
 * @author jin
 * @version $Id: DatasourceTest.java, v 0.1 2015年10月15日 下午8:20:13 jin Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:framework/spring/framework.xml",
                                   "classpath:framework/test_spring/datasource.xml",
                                   "classpath:framework/test_spring/frameworkTransaction.xml" })
public class DatasourceTest {
    @Autowired
    private TestTbDAO testTbDAO;

    @Test
    public void testInsert() {
        TestTbDO testTbDO = new TestTbDO();
        testTbDO.setId("asdfP987");
        testTbDO.setName("coco");
        testTbDAO.insert(testTbDO);
    }
}
