/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.tools.netty.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * 配置信息
 * 
 * @author Gushu
 * @version $Id: NettyClientConfig.java, v 0.1 2017年3月15日 上午9:36:38 Gushu Exp $
 */
public class NettyClientConfig {

    private static final int DEFAULT_MIN_CONN_NUM = 1;

    private static final int DEFAULT_MAX_CONN_NUM = Integer.MAX_VALUE;

    @Value("#{framework_properties['framework.netty.min.conn']}")
    private int              minConnNum;

    @Value("#{framework_properties['framework.netty.max.conn']}")
    private int              maxConnNum;

    @Value("#{framework_properties['framework.netty.conn.host']}")
    private String           host;

    @Value("#{framework_properties['framework.netty.conn.port']}")
    private int              port;

    public int getMinConnNum() {

        if (minConnNum <= 0) {
            return DEFAULT_MIN_CONN_NUM;
        }
        return minConnNum;
    }

    public int getMaxConnNum() {
        if (maxConnNum <= 0 || maxConnNum < minConnNum) {
            return DEFAULT_MAX_CONN_NUM;
        }
        return maxConnNum;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
