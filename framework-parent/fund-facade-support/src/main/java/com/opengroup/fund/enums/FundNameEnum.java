package com.opengroup.fund.enums;

public enum FundNameEnum {

    /**
     * С����ֵ������ʱ�����߽���ͨ��
     */
    NON_FUND("NON_FUND", "�ǽ���"),

    UNIONPAY("UNIONPAY", "����"),

    ABC("ABC", "ũ��");

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

    private FundNameEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static FundNameEnum findByCode(String code) {
        for (FundNameEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
