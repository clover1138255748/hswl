/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.common.unit;

import java.math.BigDecimal;

/**
 * 
 * @author jin
 * @version $Id: CurrencyEnum.java, v 0.1 2015年9月6日 上午10:05:47 jin Exp $
 */
public enum CurrencyEnum {

    CNY("CNY", "￥", 1, "人民币"),

    USD("USD", "$", 6.15, "美元");

    private String     code;

    private String     symbol;

    private BigDecimal rate;

    private String     txt;

    private CurrencyEnum(String code, String symbol, double rate, String txt) {
        this.code = code;
        this.symbol = symbol;
        this.rate = new BigDecimal(String.valueOf(rate));
        this.txt = txt;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public String getTxt() {
        return txt;
    }

    public static CurrencyEnum findByCode(String code) {
        for (CurrencyEnum item : CurrencyEnum.values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }

}
