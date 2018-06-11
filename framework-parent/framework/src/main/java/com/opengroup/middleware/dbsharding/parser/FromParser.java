/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding.parser;

import java.util.Map;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;

import com.opengroup.middleware.dbsharding.ShardingTableDef;

/**
 * 
 * @author jin
 * @version $Id: FromParser.java, v 0.1 2015年10月10日 下午7:06:53 jin Exp $
 */
public class FromParser implements FromItemVisitor {

    public static FromParser instance = new FromParser();

    private FromParser() {
    }

    @Override
    public void visit(ValuesList arg0) {
    }

    @Override
    public void visit(LateralSubSelect arg0) {
    }

    @Override
    public void visit(SubJoin arg0) {
    }

    @Override
    public void visit(SubSelect arg0) {
        arg0.getSelectBody().accept(SelectParser.instance);
    }

    @Override
    public void visit(Table table) {
        String tableName = table.getName().toLowerCase();
        Map<String, ShardingTableDef> dsShardingDef = TmpData.currentDsDef.get();
        ShardingTableDef tableDef = dsShardingDef.get(tableName);
        if (tableDef != null) {
            NeedShardingTableEntity entity = new NeedShardingTableEntity();
            entity.setTable(table);
            entity.setTableDef(tableDef);
            TmpData.needShardingTables.get().add(entity);
            TmpData.currentExecuteTableDef.set(entity);
        }
    }
}
