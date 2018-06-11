package com.opengroup.fund.enums;

/**
 * 负债账户金额状态枚举
 * 
 * @author laoying
 * @version $Id: LiabilityAmountType.java, v 0.1 2015年10月13日 下午2:27:38 laoying Exp $
 */
public enum LiabilityAmountTypeEnum {
    
    AVAILABLE(1,"可用"),
    
    FREEZE(2,"冻结");
    
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
