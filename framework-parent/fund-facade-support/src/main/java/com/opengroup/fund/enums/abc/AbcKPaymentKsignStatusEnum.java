/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.fund.enums.abc;


/**
 * 
 * @author wangdawei
 * @version $Id: AbcKPaymentKsignStatusEnum.java, v 0.1 2017年9月28日 下午3:18:41 wangdawei Exp $<br/>
 * 农行K码支付签约状态枚举
 * 参考了电商的相关枚举：/hsshop-web/src/main/java/com/runlion/shop/entity/enums/BankCardStateEnum.java
 */
public enum AbcKPaymentKsignStatusEnum {
    //农行K码支付签约状态（0：未签约，1：已签约，2：申请解除签约，3：已解除签约）
    NO_SIGN("0","未签约"),
    HAVE_SIGN("1","已签约"),
    APPLY_RELIEVE_SIGN("2","申请解除签约"),
    HAVE_RELIEVE_SIGN("3","已解除签约"),
    CAN_NOT_SIGN("4", "不可签约"), 
    TEL_CHANGE_SIGN("5", "电话改签");
    /**
     * 枚举的code
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

    /**
     * @param code
     * @param description
     */
    private AbcKPaymentKsignStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }


    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static AbcKPaymentKsignStatusEnum findByCode(String code) {
        for (AbcKPaymentKsignStatusEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
    
}
