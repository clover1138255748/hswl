package com.opengroup.fund.enums;

public enum PlatformDiffStatusEnum {

    OUR_SIDE(1, "我方有订单，对方无订单"),

    OTHER_SIDE(2, "我方无订单，对方有订单"),

    DIFF(3, "数据差异"),

    BALANCE(4, "多次对账后平账");

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

    private PlatformDiffStatusEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static PlatformDiffStatusEnum parse(int value) {
        for (PlatformDiffStatusEnum item : PlatformDiffStatusEnum.values()) {
            if (value == item.value) {
                return item;
            }
        }
        return null;
    }
}
