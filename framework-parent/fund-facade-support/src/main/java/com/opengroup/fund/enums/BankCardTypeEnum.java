package com.opengroup.fund.enums;

/**
 * ���п�������ö����
 * 
 * @author laoniu
 * @version $Id: BankCardTypeEnum.java, v 0.1 2015��11��9�� ����4:18:28 laoniu Exp $
 */
public enum BankCardTypeEnum {

    DEBIT_CARD(1, "���"), // ��ǿ�

    CREDIT_CARD(2, "���ÿ�"), // ���ǿ�

    SEMI_CREDIT_CARD(3, "׼���ǿ�"),

    PREPAID_CARD(4, "Ԥ���ѿ�");

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

    private BankCardTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static BankCardTypeEnum parse(int value) {
        for (BankCardTypeEnum item : BankCardTypeEnum.values()) {
            if (value == item.value) {
                return item;
            }
        }
        return null;
    }
}
