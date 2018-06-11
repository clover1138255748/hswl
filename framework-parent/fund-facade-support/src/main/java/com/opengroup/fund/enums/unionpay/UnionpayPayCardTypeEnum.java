package com.opengroup.fund.enums.unionpay;

/**
 * ���ѽ���
 * ֧��������
 * 
 * @author laoniu
 * @version $Id: UnionpayPayCardTypeEnum.java, v 0.1 2016��4��26�� ����3:35:51 laoniu Exp $
 */
public enum UnionpayPayCardTypeEnum {

    /**
     * δ֪
     */
    UNKOWN("00", "δ֪"),

    /**
     * 
     */
    DEBIT("01", "����˻�"),

    /**
     * 
     */
    CREDIT("02", "�����˻�"),

    /**
     * 
     */
    SEMI_CREDIT("03", "׼�����˻�"),

    /**
     * 
     */
    DEBIT_CREDIT("04", "�����һ�˻�"),

    /**
     * 
     */
    PREPAID("05", "Ԥ�����˻�"),

    /**
     * 
     */
    HALF_OPEN_PREPAY("06", "�뿪��Ԥ�����˻�");

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

    private UnionpayPayCardTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static UnionpayPayCardTypeEnum findByCode(String code) {
        for (UnionpayPayCardTypeEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
