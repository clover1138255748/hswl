package com.opengroup.fund.enums.unionpay;

public enum UnionpayOrderStatusEnum {

    SUCCESS("SUCCESS", "成功"),

    FAIL("FAIL", "失败"),

    WORKING("WORKING", "交易中"),

    // 暂时没有用到
    CANCEL("CANCEL", "取消"),

    UNKOWN("UNKOWN", "未知");

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

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private UnionpayOrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static UnionpayOrderStatusEnum findByCode(String code) {
        for (UnionpayOrderStatusEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
