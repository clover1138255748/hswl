/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding.command;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;

import com.opengroup.middleware.dbsharding.ShardingTableDef;
import com.opengroup.middleware.dbsharding.parser.FromParser;
import com.opengroup.middleware.dbsharding.parser.NeedShardingTableEntity;
import com.opengroup.middleware.dbsharding.parser.ShardingParser;
import com.opengroup.middleware.dbsharding.parser.TmpData;
import com.opengroup.middleware.dbsharding.parser.WhereParser;

/**
 * 
 * @author jin
 * @version $Id: SelectShardingParser.java, v 0.1 2015年10月10日 下午5:19:27 jin Exp $
 */
public class UpdateShardingParser implements ShardingParser {

    public static UpdateShardingParser instance = new UpdateShardingParser();

    private UpdateShardingParser() {
    }

    public int doParser(Statement statement, Map<String, ShardingTableDef> tableDefMap) {
        Update update = (Update) statement;
        update.getTables().get(0).accept(FromParser.instance);

        List<NeedShardingTableEntity> entityList = TmpData.needShardingTables.get();
        if (entityList.size() > 0) {
            update.getWhere().accept(WhereParser.instance);
        }
        String shardingStr = TmpData.shardingString.get();
        if (ShardingStringValidator.isShardingString(shardingStr)) {
            return entityList.get(0).executeSharding(shardingStr);
        }
        return 0;
    }
}
