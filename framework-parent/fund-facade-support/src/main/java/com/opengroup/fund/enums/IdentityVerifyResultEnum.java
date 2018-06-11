package com.opengroup.fund.enums;


public enum IdentityVerifyResultEnum {
    /**
     * ���������֤�ɹ�
     */
    TRUE("TRUE", true, "��֤ͨ��"),
    /**
     * ���п���������ʵ���������֤����ȷ����������п�����ȷ
     */
    BANK_CARK_ERROR("BANK_CARK_ERROR", false, "���п�������"),
    /**
     * ���п�Ԥ���ֻ��Ų���
     */
    MOBILE_NO_ERROR("MOBILE_NO_ERROR", false, "���п�Ԥ���ֻ��Ų���");

    /**
     * ��������Ƿ���ʵ
     */
    private boolean trueIdentity;
    /**
     * ö�ٵ�code
     */
    private String  code;
    /**
     * ����
     */
    private String  description;

    private IdentityVerifyResultEnum(String code, boolean trueIdentity, String description) {
        this.code = code;
        this.trueIdentity = trueIdentity;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static IdentityVerifyResultEnum findByCode(String code) {
        for (IdentityVerifyResultEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public boolean isTrueIdentity() {
        return trueIdentity;
    }

    public void setTrueIdentity(boolean trueIdentity) {
        this.trueIdentity = trueIdentity;
    }

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
}
