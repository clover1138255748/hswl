/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.common.sql;

/**
 * �ֿ�ֱ�λ��ö��
 * @author jin
 * @version $Id: ShardingIdEnum.java, v 0.1 2015��7��3�� ����11:58:19 jin Exp $
 */
public enum ShardingIdEnum {

    NO_SHARDING("A000", "�����зֿ�ֱ�"),

    FAIL_OVER("F000", "������FAIL��"),

    SHADLE("S000", "Ӱ�ӿ�");

    private ShardingIdEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
