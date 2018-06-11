package com.opengroup.fund.enums.gwtrust;

/**
 * ɨ���ά��֧��-���������׶���֧����Դ:1.ɨ�����ѣ�2.�̳����ѣ�3.��������
 * 
 * @author chengqiang
 * @version $Id: TradeSourceEnum.java, v 0.1 2017��9��12�� ����8:05:40 chengqiang Exp $
 */
public enum TradeSourceEnum {
    /**
     * 
     * 1.ɨ������
     */
    QRCODE(1, "ɨ������"),

    /**
     * 2.�̳�����
     */
    MALL(2, "�̳�����"),
    /**
     * 
     * 3.����
     */
    OIL(3, "��������");

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
