package com.opengroup.fund.enums.unionpay;

/**
 * ������������
 * 
 * @author laoniu
 * @version $Id: UnionpayChannelTypeEnum.java, v 0.1 2016��5��30�� ����10:31:13 laoniu Exp $
 */
public enum UnionpayChannelTypeEnum {
    VOICE("05", "����"),

    INTERNET("07", "������"),

    MOBILE("08", "�ƶ�");

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

    private UnionpayChannelTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static UnionpayChannelTypeEnum findByCode(String code) {
        for (UnionpayChannelTypeEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
