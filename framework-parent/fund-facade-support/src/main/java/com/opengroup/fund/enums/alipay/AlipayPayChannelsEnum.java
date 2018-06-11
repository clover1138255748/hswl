package com.opengroup.fund.enums.alipay;

/**
 * 支付宝支付，渠道类型
 * App支付申请支付biz_content参数中子参数enable_pay_channels、disable_pay_channels的值
 * 
 * @author laoniu
 * @version $Id: AlipayPayChannelsEnum.java, v 0.1 2016年11月25日 下午2:44:35 laoniu Exp $
 */
public enum AlipayPayChannelsEnum {

    BALANCE("balance", "余额"),

    MONEY_FUND("moneyFund", "余额宝"),

    COUPON("coupon", "红包"),

    PCREDIT("pcredit", "花呗"),

    PCREDITPAY_INSTALLMENT("pcreditpayInstallment", "花呗分期"),

    CREDIT_CARD("creditCard", "信用卡"),

    CREDIT_CARD_EXPRESS("creditCardExpress", "信用卡快捷"),

    CREDIT_CARD_CARTOON("creditCardCartoon", "信用卡卡通"),

    CREDIT_GROUP("credit_group", "信用支付类型（包含信用卡卡通、信用卡快捷、花呗、花呗分期）"),

    DEBIT_CARD_EXPRESS("debitCardExpress", "借记卡快捷"),

    MCARD("mcard", "商户预存卡"),

    PCARD("pcard", "个人预存卡"),

    PROMOTIO("promotion", "优惠（包含实时优惠+商户优惠）");

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

    private AlipayPayChannelsEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static AlipayPayChannelsEnum findByCode(String code) {
        for (AlipayPayChannelsEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
