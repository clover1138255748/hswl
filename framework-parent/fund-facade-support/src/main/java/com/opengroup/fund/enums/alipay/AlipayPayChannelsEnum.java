package com.opengroup.fund.enums.alipay;

/**
 * ֧����֧������������
 * App֧������֧��biz_content�������Ӳ���enable_pay_channels��disable_pay_channels��ֵ
 * 
 * @author laoniu
 * @version $Id: AlipayPayChannelsEnum.java, v 0.1 2016��11��25�� ����2:44:35 laoniu Exp $
 */
public enum AlipayPayChannelsEnum {

    BALANCE("balance", "���"),

    MONEY_FUND("moneyFund", "��"),

    COUPON("coupon", "���"),

    PCREDIT("pcredit", "����"),

    PCREDITPAY_INSTALLMENT("pcreditpayInstallment", "���·���"),

    CREDIT_CARD("creditCard", "���ÿ�"),

    CREDIT_CARD_EXPRESS("creditCardExpress", "���ÿ����"),

    CREDIT_CARD_CARTOON("creditCardCartoon", "���ÿ���ͨ"),

    CREDIT_GROUP("credit_group", "����֧�����ͣ��������ÿ���ͨ�����ÿ���ݡ����¡����·��ڣ�"),

    DEBIT_CARD_EXPRESS("debitCardExpress", "��ǿ����"),

    MCARD("mcard", "�̻�Ԥ�濨"),

    PCARD("pcard", "����Ԥ�濨"),

    PROMOTIO("promotion", "�Żݣ�����ʵʱ�Ż�+�̻��Żݣ�");

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

    private AlipayPayChannelsEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static AlipayPayChannelsEnum findByCode(String code) {
        for (AlipayPayChannelsEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
