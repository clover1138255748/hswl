package com.opengroup.fund.enums;

public enum FundNameEnum {

    /**
     * 小二充值、提现时，不走金融通道
     */
    NON_FUND("NON_FUND", "非金融"),

    UNIONPAY("UNIONPAY", "银联"),

    ABC("ABC", "农行");

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

    private FundNameEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static FundNameEnum findByCode(String code) {
        for (FundNameEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
