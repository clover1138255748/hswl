package com.opengroup.fund.enums.gwtrust;

/**
 * ֧����ʽö��
 * @author lulei
 * @date 2017��6��16�� ����2:07:11
 */
public enum PayWayEnum {

    /**
     * �������߳�ֵ��С�����³�ֵ��¼
     */
    BASE_PAY(1, "�ֽ����֧��"),

    /**
     * �����������ֺ�С���������ֲ�¼
     */
    TYRE_PAY(6, "��̥���֧��"),

    /**
     * ����ת�˸����˻��Լ��˻��仮ת
     */
    OIL_PAY(5, "�Ϳ����֧��"),

    /**
     * �̳�����֧��
     */
    ON_LINE_PAY(7, "����֧��"),
    /**
     * ����
     */
    CONSUMPTION(11, "�������֧��");
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
