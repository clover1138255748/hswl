package com.opengroup.fund.enums;

public enum PlatformDiffStatusEnum {

    OUR_SIDE(1, "�ҷ��ж������Է��޶���"),

    OTHER_SIDE(2, "�ҷ��޶������Է��ж���"),

    DIFF(3, "���ݲ���"),

    BALANCE(4, "��ζ��˺�ƽ��");

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
