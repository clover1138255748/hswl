package com.opengroup.fund.enums;


public enum IdentityVerifyResultEnum {
    /**
     * 卡与身份验证成功
     */
    TRUE("TRUE", true, "验证通过"),
    /**
     * 银行卡号有误：真实姓名、身份证号正确的情况，银行卡不正确
     */
    BANK_CARK_ERROR("BANK_CARK_ERROR", false, "银行卡号有误"),
    /**
     * 银行卡预留手机号不符
     */
    MOBILE_NO_ERROR("MOBILE_NO_ERROR", false, "银行卡预留手机号不符");

    /**
     * 卡与身份是否真实
     */
    private boolean trueIdentity;
    /**
     * 枚举的code
     */
    private String  code;
    /**
     * 描述
     */
    private String  description;

    private IdentityVerifyResultEnum(String code, boolean trueIdentity, String description) {
        this.code = code;
        this.trueIdentity = trueIdentity;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static IdentityVerifyResultEnum findByCode(String code) {
        for (IdentityVerifyResultEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public boolean isTrueIdentity() {
        return trueIdentity;
    }

    public void setTrueIdentity(boolean trueIdentity) {
        this.trueIdentity = trueIdentity;
    }

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
}
