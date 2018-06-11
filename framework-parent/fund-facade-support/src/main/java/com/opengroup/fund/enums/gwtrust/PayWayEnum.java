package com.opengroup.fund.enums.gwtrust;

/**
 * 支付方式枚举
 * @author lulei
 * @date 2017年6月16日 下午2:07:11
 */
public enum PayWayEnum {

    /**
     * 包含在线充值和小二线下充值补录
     */
    BASE_PAY(1, "现金余额支付"),

    /**
     * 包含在线提现和小二线下提现补录
     */
    TYRE_PAY(6, "轮胎余额支付"),

    /**
     * 包含转账给他人或自己账户间划转
     */
    OIL_PAY(5, "油卡余额支付"),

    /**
     * 商城在线支付
     */
    ON_LINE_PAY(7, "在线支付"),
    /**
     * 消费
     */
    CONSUMPTION(11, "消费余额支付");
    private int    type;

    private String desc;

    private PayWayEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static PayWayEnum findByType(int type) {
        for (PayWayEnum item : values()) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }

}
