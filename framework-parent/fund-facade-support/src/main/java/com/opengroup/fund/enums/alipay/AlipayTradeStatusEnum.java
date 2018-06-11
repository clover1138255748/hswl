package com.opengroup.fund.enums.alipay;

/**
 * ֧����֧��Trade״̬
 * @author laoniu
 * @version $Id: AlipayTradeStatusEnum.java, v 0.1 2016��11��29�� ����3:38:57 laoniu Exp $
 */
public enum AlipayTradeStatusEnum {

    WAIT_BUYER_PAY("WAIT_BUYER_PAY", "���״������ȴ���Ҹ���"),

    TRADE_CLOSED("TRADE_CLOSED", "δ����׳�ʱ�رգ���֧����ɺ�ȫ���˿�"),

    TRADE_SUCCESS("TRADE_SUCCESS", "����֧���ɹ�"),

    TRADE_FINISHED("TRADE_FINISHED", "���׽����������˿�");

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

    private AlipayTradeStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static AlipayTradeStatusEnum findByCode(String code) {
        for (AlipayTradeStatusEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
