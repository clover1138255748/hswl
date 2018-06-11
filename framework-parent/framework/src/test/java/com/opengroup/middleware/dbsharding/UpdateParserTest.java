/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding;

import java.util.HashMap;
import java.util.Map;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

import org.junit.Assert;
import org.junit.Test;

import com.opengroup.middleware.dbsharding.command.UpdateShardingParser;
import com.opengroup.middleware.dbsharding.parser.TmpData;

/**
 * 
 * @author jin
 * @version $Id: InsertParserTest.java, v 0.1 2015年10月14日 下午8:23:27 jin Exp $
 */
public class UpdateParserTest {

    @Test
    public void insertTest() throws Exception {
        Map<String, ShardingTableDef> dsDefMap = new HashMap<String, ShardingTableDef>();
        ShardingTableDef def1 = new ShardingTableDef();
        def1.setShardingColumn("bidder_id");
        def1.setTableName("bid_item");
        dsDefMap.put(def1.getTableName(), def1);

        TmpData.init(dsDefMap, 2);
        String sql = "update bid_item set id='coco', name='kaka' where status='sisi' and bidder_id='aaP998'";
        Statement statement = CCJSqlParserUtil.parse(sql);
        UpdateShardingParser parser = UpdateShardingParser.instance;
        int dsIdx = parser.doParser(statement, null);
        System.out.println(statement.toString());
        Assert.assertEquals(dsIdx, 1);
    }
}
