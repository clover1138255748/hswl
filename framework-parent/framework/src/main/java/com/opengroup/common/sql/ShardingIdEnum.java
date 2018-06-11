/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.common.sql;

/**
 * 分库分表位的枚举
 * @author jin
 * @version $Id: ShardingIdEnum.java, v 0.1 2015年7月3日 上午11:58:19 jin Exp $
 */
public enum ShardingIdEnum {

    NO_SHARDING("A000", "不进行分库分表"),

    FAIL_OVER("F000", "数据在FAIL库"),

    SHADLE("S000", "影子库");

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
