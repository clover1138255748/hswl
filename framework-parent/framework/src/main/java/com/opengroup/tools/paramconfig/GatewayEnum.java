package com.opengroup.tools.paramconfig;

/**
 * ����ö��
 * @author mingzhou.lu
 * @version $Id: GatewayEnum.java, v 0.1 2017��7��10�� ����11:32:11  mingzhou.lu Exp $
 */
public enum GatewayEnum {

    /**
     * ��������
     */
    GWRP(1, "gwrp"),
    /**
     * ˾������
     */
    GSEJ(2, "gwsj"),
    /**
     * �ʽ��������
     */
    GWTRUST(3, "gwtrust"),
    /**
     * ��������
     */
    GWCZ(4, "gwcz"),
    /**
     * ��������
     */
    GWHZ(5, "gwhz"),
    /**
     * ����վ����
     */
    GWHYZ(6, "gwhyz"),
    /**
     * С��
     */
    GWCRM(7, "gwcrm"),
    /**
     * ����������
     */
    GWTHIRDPT(8, "gwthirdpt"),
    /**
     * �����¼����
     */
    GWLOGIN(9, "gwlogin"),
    /**
     * վ������
     */
    GWWWW(10, "gwwww"),
    /**
     * ��άͳһ����
     */
    RELEASE(11, "release"),
    GWAPP(12, "gwapp");

    private int    value;

    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private GatewayEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static GatewayEnum parse(int value) {
        for (GatewayEnum item : GatewayEnum.values()) {
            if (value == item.value) {
                return item;
            }
        }
        return null;
    }
}
