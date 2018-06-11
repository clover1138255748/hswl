package com.opengroup.tools.solr.beans.params;

/**
 * ���������
 * 
 * @author ijavoracle
 * @version $Id: BooleanOperator.java, v 0.1 2016��1��18�� ����3:47:33 ijavoracle Exp $
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
