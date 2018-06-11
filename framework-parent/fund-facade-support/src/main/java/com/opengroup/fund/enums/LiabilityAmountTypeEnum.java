package com.opengroup.fund.enums;

/**
 * ��ծ�˻����״̬ö��
 * 
 * @author laoying
 * @version $Id: LiabilityAmountType.java, v 0.1 2015��10��13�� ����2:27:38 laoying Exp $
 */
public enum LiabilityAmountTypeEnum {
    
    AVAILABLE(1,"����"),
    
    FREEZE(2,"����");
    
    private int value;
    
    private String text;
    
    private LiabilityAmountTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

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

    public static LiabilityAmountTypeEnum parse(int value) {
        for(LiabilityAmountTypeEnum item : LiabilityAmountTypeEnum.values()) {
            if(value == item.value) {
                return item;
            }
        }
        return null;
    }
    
}
