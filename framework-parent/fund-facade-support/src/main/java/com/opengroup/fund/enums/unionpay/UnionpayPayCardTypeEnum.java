package com.opengroup.fund.enums.unionpay;

/**
 * 消费交易
 * 支付卡类型
 * 
 * @author laoniu
 * @version $Id: UnionpayPayCardTypeEnum.java, v 0.1 2016年4月26日 下午3:35:51 laoniu Exp $
 */
public enum UnionpayPayCardTypeEnum {

    /**
     * 未知
     */
    UNKOWN("00", "未知"),

    /**
     * 
     */
    DEBIT("01", "借记账户"),

    /**
     * 
     */
    CREDIT("02", "贷记账户"),

    /**
     * 
     */
    SEMI_CREDIT("03", "准贷记账户"),

    /**
     * 
     */
    DEBIT_CREDIT("04", "借贷合一账户"),

    /**
     * 
     */
    PREPAID("05", "预付费账户"),

    /**
     * 
     */
    HALF_OPEN_PREPAY("06", "半开放预付费账户");

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

    private UnionpayPayCardTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static UnionpayPayCardTypeEnum findByCode(String code) {
        for (UnionpayPayCardTypeEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
