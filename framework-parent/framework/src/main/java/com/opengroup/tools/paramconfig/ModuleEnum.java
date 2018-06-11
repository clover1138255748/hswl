package com.opengroup.tools.paramconfig;

/**
 * ģ��ö��
 * @author mingzhou.lu
 * @version $Id: ModuleEnum.java, v 0.1 2017��7��10�� ����11:32:23  mingzhou.lu Exp $
 */
public enum ModuleEnum {

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
     * ����
     */
    FINANCIAL(10, "financial"),
    /**
     * ҵ���û�
     */
    UIC(11, "uic"),
    /**
     * ����ϵͳ
     */
    WO(12, "wo"),
    /**
     * �û�����
     */
    USERMANAGER(13, "usermanager"),
    /**
     * ����������
     */
    WF(13, "wf"),
    /**
     * �ʽ��û�
     */
    CIF(14, "cif"),
    /**
     * ����Ա����
     */
    TRADEGUIDE(15, "tradeguide"),
    /**
     *��Ϣ����
     */
    MSGCENTER(16, "msgcenter"),
    /**
     *��������
     */
    COMMINSERIVCE(17, "commonservice"),
    /**
     *�������
     */
    FRAMEWORK(18, "framework"),
    /**
     *Ӧ�չ���
     */
    RECEIPT(19, "receipt"),
    /**
     *����ģ��
     */
    CARRIER(20, "carrier"),
    GOODSOWNER(21, "goodsowner"),
    GWWWW(22, "gwwww"),
    RELEASE(23, "release"),
    GWAPP(24, "gwapp");

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

    private ModuleEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static ModuleEnum parse(int value) {
        for (ModuleEnum item : ModuleEnum.values()) {
            if (value == item.value) {
                return item;
            }
        }
        return null;
    }
}
