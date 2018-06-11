package com.opengroup.fund.enums.gwtrust;

/**
 * 扫描二维码支付-订单：交易订单支付来源:1.扫码消费，2.商城消费，3.提油消费
 * 
 * @author chengqiang
 * @version $Id: TradeSourceEnum.java, v 0.1 2017年9月12日 下午8:05:40 chengqiang Exp $
 */
public enum TradeSourceEnum {
    /**
     * 
     * 1.扫码消费
     */
    QRCODE(1, "扫码消费"),

    /**
     * 2.商城消费
     */
    MALL(2, "商城消费"),
    /**
     * 
     * 3.提油
     */
    OIL(3, "提油消费");

    private int    type;

    private String desc;

    private TradeSourceEnum(int type, String desc) {
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

    public static TradeSourceEnum findByType(int type) {
        for (TradeSourceEnum item : values()) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }

}
