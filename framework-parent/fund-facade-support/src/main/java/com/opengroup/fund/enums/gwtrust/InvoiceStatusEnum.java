package com.opengroup.fund.enums.gwtrust;

/**
 * 扫描二维码支付-订单：发票状态:
 * 
 * @author chengqiang
 * @version $Id: InvoiceStatusEnum.java, v 0.1 2017年9月12日 下午8:05:40 chengqiang Exp $
 */
public enum InvoiceStatusEnum {
    /**
     * 
     * 1.未开发票，
     */
    NOT_INVOICE(1, "未开发票"),

    /**
     * 2.已开发票
     */
    INVOICE(2, "已开发票"),
    /**
     * 3.部分已开发票
     */
    PARTIAL_INVOICE(3, "部分已开发票");
    private int    type;

    private String desc;

    private InvoiceStatusEnum(int type, String desc) {
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

    public static InvoiceStatusEnum findByType(int type) {
        for (InvoiceStatusEnum item : values()) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }

}
