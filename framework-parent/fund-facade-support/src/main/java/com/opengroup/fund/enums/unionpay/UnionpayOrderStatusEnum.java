package com.opengroup.fund.enums.unionpay;

public enum UnionpayOrderStatusEnum {

    SUCCESS("SUCCESS", "�ɹ�"),

    FAIL("FAIL", "ʧ��"),

    WORKING("WORKING", "������"),

    // ��ʱû���õ�
    CANCEL("CANCEL", "ȡ��"),

    UNKOWN("UNKOWN", "δ֪");

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

    private UnionpayOrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static UnionpayOrderStatusEnum findByCode(String code) {
        for (UnionpayOrderStatusEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
