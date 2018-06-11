package com.opengroup.fund.enums.gwtrust;

/**
 * ɨ���ά��֧��-��������Ʊ״̬:
 * 
 * @author chengqiang
 * @version $Id: InvoiceStatusEnum.java, v 0.1 2017��9��12�� ����8:05:40 chengqiang Exp $
 */
public enum InvoiceStatusEnum {
    /**
     * 
     * 1.δ����Ʊ��
     */
    NOT_INVOICE(1, "δ����Ʊ"),

    /**
     * 2.�ѿ���Ʊ
     */
    INVOICE(2, "�ѿ���Ʊ"),
    /**
     * 3.�����ѿ���Ʊ
     */
    PARTIAL_INVOICE(3, "�����ѿ���Ʊ");
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
