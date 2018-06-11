package com.opengroup.fund.enums;

/**
 * 银行卡号类型枚举类
 * 
 * @author laoniu
 * @version $Id: BankCardTypeEnum.java, v 0.1 2015年11月9日 下午4:18:28 laoniu Exp $
 */
public enum BankCardTypeEnum {

    DEBIT_CARD(1, "储蓄卡"), // 借记卡

    CREDIT_CARD(2, "信用卡"), // 贷记卡

    SEMI_CREDIT_CARD(3, "准贷记卡"),

    PREPAID_CARD(4, "预付费卡");

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
