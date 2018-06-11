package com.opengroup.tools.solr.beans.params;

/**
 * 布尔运输符
 * 
 * @author ijavoracle
 * @version $Id: BooleanOperator.java, v 0.1 2016年1月18日 下午3:47:33 ijavoracle Exp $
 */
public enum BooleanOperator {
    AND("+"), OR(""), NOT("-");

    private String symbol;

    private BooleanOperator(String symbol) {
        this.symbol = symbol;
    }

    public String valueOf() {
        return this.symbol;
    }
}
