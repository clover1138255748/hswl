package com.opengroup.fund.enums.unionpay;

/**
 * ��������ļ����״̬
 * 
 * @author laoniu
 * @version $Id: UnionpayLoadStatusEnum.java, v 0.1 2016��5��23�� ����11:11:26 laoniu Exp $
 */
public enum UnionpayLoadStatusEnum {
    SUCCESS("SUCCESS", "�ɹ�"),

    FAIL("FAIL", "ʧ��");

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

    private UnionpayLoadStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static UnionpayLoadStatusEnum findByCode(String code) {
        for (UnionpayLoadStatusEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
