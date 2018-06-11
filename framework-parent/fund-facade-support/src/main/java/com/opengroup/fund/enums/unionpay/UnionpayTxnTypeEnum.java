package com.opengroup.fund.enums.unionpay;

/**
 * ��������
 * 
 * @author laoniu
 * @version $Id: UnionpayPayTxnTypeEnum.java, v 0.1 2016��4��26�� ����4:15:13 laoniu Exp $
 */
public enum UnionpayTxnTypeEnum {

    QUERY("00", "��ѯ����"),

    CONSUME("01", "����"),

    PRE_AUTH("02", "Ԥ��Ȩ"),

    PRE_AUTH_FINISHED("03", "Ԥ��Ȩ���"),

    RETURN_GOODS("04", "�˿�"),

    /**
     * credit for load��Ȧ�� debit for load��
     */
    CREDIT_FOR_LOAD("05", "Ȧ��"),

    COLLECTING_FOR_ANOTHER_AGENCY("11", "����"),

    PAY_FOR_ANOTHER("12", "����"),

    BILL_PAY("13", "�˵�֧��"),

    TRANSFER_ACCOUNTS("14", "ת�ˣ�������"),

    BATCH_TRANSACTIONS("21", "��������"),

    BATCH_QUERY("22", "������ѯ"),

    CONSUME_CANCEL("31", "���ѳ���"),

    PRE_AUTH_CANCEL("32", "Ԥ��Ȩ����"),

    PRE_AUTH_FINISHED_CANCEL("33", "Ԥ��Ȩ��ɳ���"),

    BALANCE_QUERY("71", "����ѯ"),

    CERTIFICATION_AND_BIND("72", "ʵ����֤-�����󶨹�ϵ"),

    ACCOUNT_STATEMENT_QUERY("73", "�˵���ѯ "),

    UNBIND("74", "����󶨹�ϵ"),

    BIND_QUERY("75", "��ѯ�󶨹�ϵ"),

    SEND_SMS_CODE("77", "���Ͷ�����֤�뽻��"),

    OPEN_QUERY_TRANSACTIONS("78", "��ͨ��ѯ����"),

    OPEN_TRANSACTIONS("79", "��ͨ����"),

    IC_CARD_SHELL_NOTICE("94", "IC ���ű�֪ͨ");

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

    private UnionpayTxnTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static UnionpayTxnTypeEnum findByCode(String code) {
        for (UnionpayTxnTypeEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
