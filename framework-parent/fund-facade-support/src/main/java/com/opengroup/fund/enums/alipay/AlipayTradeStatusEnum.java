package com.opengroup.fund.enums.alipay;

/**
 * 支付宝支付Trade状态
 * @author laoniu
 * @version $Id: AlipayTradeStatusEnum.java, v 0.1 2016年11月29日 下午3:38:57 laoniu Exp $
 */
public enum AlipayTradeStatusEnum {

    WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建，等待买家付款"),

    TRADE_CLOSED("TRADE_CLOSED", "未付款交易超时关闭，或支付完成后全额退款"),

    TRADE_SUCCESS("TRADE_SUCCESS", "交易支付成功"),

    TRADE_FINISHED("TRADE_FINISHED", "交易结束，不可退款");

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

    private AlipayTradeStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static AlipayTradeStatusEnum findByCode(String code) {
        for (AlipayTradeStatusEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
