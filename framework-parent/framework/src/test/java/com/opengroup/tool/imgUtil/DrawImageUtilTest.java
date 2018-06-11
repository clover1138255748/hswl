/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.opengroup.tool.imgUtil;

import org.junit.Test;

import com.opengroup.tools.image.DrawImageUtil;

/**
 * 
 * @author jin
 * @version $Id: DrawImageUtilTest.java, v 0.1 2016年6月28日 上午11:46:29 jin Exp $
 */
public class DrawImageUtilTest {
    @Test
    public void tt() throws Exception {
        DrawImageUtil.drawTel("1585_712-30.04", "D:\\data\\1.jpg", "D:\\data\\bg.png");
    }
}
