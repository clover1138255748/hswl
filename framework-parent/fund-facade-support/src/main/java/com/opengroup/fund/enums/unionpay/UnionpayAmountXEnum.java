package com.opengroup.fund.enums.unionpay;

/**
 * �������
 * 
 * @author laoniu
 * @version $Id: UnionpayAmountXEnum.java, v 0.1 2016��5��12�� ����2:44:18 laoniu Exp $
 */
public enum UnionpayAmountXEnum {

    C("C", "����"),

    D("D", "���");

    /**
     * ö�ٵ�code
     */
    private String code;
    /**
     * ����
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

    private UnionpayAmountXEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static UnionpayAmountXEnum findByCode(String code) {
        for (UnionpayAmountXEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
