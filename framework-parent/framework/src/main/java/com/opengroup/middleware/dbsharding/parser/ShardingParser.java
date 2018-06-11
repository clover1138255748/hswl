/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding.parser;

import java.util.Map;

import net.sf.jsqlparser.statement.Statement;

import com.opengroup.middleware.dbsharding.ShardingTableDef;

/**
 * 
 * @author jin
 * @version $Id: ShardingParser.java, v 0.1 2015年10月10日 下午5:23:39 jin Exp $
 */
public interface ShardingParser {
    /**
     * 返回下标
     * 
     * @return
     */
    int doParser(Statement statement, Map<String, ShardingTableDef> tableDefMap);
}
