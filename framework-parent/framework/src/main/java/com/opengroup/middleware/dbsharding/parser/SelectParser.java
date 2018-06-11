/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.middleware.dbsharding.parser;

import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

import com.opengroup.exception.BaseFrameworkException;

/**
 * 
 * @author jin
 * @version $Id: SelectParser.java, v 0.1 2015年10月10日 下午7:09:12 jin Exp $
 */
public class SelectParser implements SelectVisitor {
    public static SelectParser instance = new SelectParser();

    private SelectParser() {
    }

    @Override
    public void visit(WithItem arg0) {
        throw new BaseFrameworkException("not support WithItem");
    }

    @Override
    public void visit(SetOperationList arg0) {
        throw new BaseFrameworkException("not support SetOperationList");
    }

    @Override
    public void visit(PlainSelect arg0) {
        arg0.getFromItem().accept(FromParser.instance);
        arg0.getWhere().accept(WhereParser.instance);
        TmpData.currentExecuteTableDef.remove();
    }
}
