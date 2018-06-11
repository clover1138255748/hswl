/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.fund.enums.gwtrust;

public enum TradeRecordType {
    /**
     * ����
     */
    INCOME("INCOME"),
    /**
     * ֧��
     */
    OUTCOME("OUTCOME"),
    /**
     * ͬ�û��ڲ�ת
     */
    SELF("SELF");

    private String code;

    public static TradeRecordType findByCode(String code) {
        for (TradeRecordType item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    private TradeRecordType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
