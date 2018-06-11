/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.fund.enums.abc;


/**
 * 
 * @author wangdawei
 * @version $Id: AbcKPaymentKsignStatusEnum.java, v 0.1 2017��9��28�� ����3:18:41 wangdawei Exp $<br/>
 * ũ��K��֧��ǩԼ״̬ö��
 * �ο��˵��̵����ö�٣�/hsshop-web/src/main/java/com/runlion/shop/entity/enums/BankCardStateEnum.java
 */
public enum AbcKPaymentKsignStatusEnum {
    //ũ��K��֧��ǩԼ״̬��0��δǩԼ��1����ǩԼ��2��������ǩԼ��3���ѽ��ǩԼ��
    NO_SIGN("0","δǩԼ"),
    HAVE_SIGN("1","��ǩԼ"),
    APPLY_RELIEVE_SIGN("2","������ǩԼ"),
    HAVE_RELIEVE_SIGN("3","�ѽ��ǩԼ"),
    CAN_NOT_SIGN("4", "����ǩԼ"), 
    TEL_CHANGE_SIGN("5", "�绰��ǩ");
    /**
     * ö�ٵ�code
     */
    private String code;
    /**
     * ����
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
     * ͨ��CODE����ö��
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
