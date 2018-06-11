package com.opengroup.fund.enums.unionpay;

/**
 * �����̻���ȡ�ļ�����
 * 
 * @author laoniu
 * @version $Id: UnionpayAcctStatementFileEnum.java, v 0.1 2016��4��26�� ����11:36:56 laoniu Exp $
 */
public enum UnionpayAcctStatementFileEnum {

    /**
     * �̻��ţ� 15 λ�� _����(YYYYMMDD).zip
     */
    TRANSACTION_SERIAL("00", "������ˮ�ļ�", "zip"),

    /**
     * CUPMBCC_Remittance_Daily_Report_����(YYYYMMDD).csv
     */
    CUPMBCC_Remittance_Daily_Report("81", "����ջ���", "csv"),

    /**
     * CUPMBCC_Settlement_Daily_Report_����(YYYYMMDD).csv
     */
    CUPMBCC_Settlement_Daily_Report("82", "�����ջ���", "csv"),

    /**
     * CUPMBCC_Settlement_Monthly_Report_�·�(YYYYMM)01.csv
     */
    CUPMBCC_Settlement_Monthly_Report("83", "�����»���", "csv"),

    /**
     * CUPMBCC_Chargeback_Daily_Report_����(YYYYMMDD).csv
     */
    CUPMBCC_Chargeback_Daily_Report("84", "�۷��ջ���", "csv"),

    /**
     * �յ������ţ� 8 λ�� _�̻���(15 λ)_out.tar.gz
     */
    TRADE_SERIAL("85", "�յ���ˮ�ļ�", "tar.gz"),

    /**
     * CUPS_�̻��ţ� 15 λ�� _��������(YYYYMMDD)
     */
    CUPS_SERIAL("87", "CUPS��ˮ�ļ�", ""),

    /**
     * MC �̻���(�� 8 λ)��������(YYMMDD)_GRP
     */
    MC_GRP("88", "MC_GRP", ""),

    /**
     * MC �̻���(�� 8 λ)��������(YYMMDD)
     */
    MC("89", "MC", ""),

    /**
     * MC �̻���(�� 8 λ)��������(YYMMDD)_GNETE
     */
    MC_GNETE("90", "MC_GNETE", ""),

    /**
     * WKDS �̻��ţ� 15 λ���������ڣ� YYMMDD��
     */
    WKDS("91", "WKDS", "");

    /**
     * ö�ٵ�code
     */
    private String code;
    /**
     * ����
     */
    private String description;

    /**
     * �ļ���β
     */
    private String endWith;

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

    public String getEndWith() {
        return endWith;
    }

    public void setEndWith(String endWith) {
        this.endWith = endWith;
    }

    private UnionpayAcctStatementFileEnum(String code, String description, String endWith) {
        this.code = code;
        this.description = description;
        this.endWith = endWith;
    }

    /**
     * ͨ��CODE����ö��
     * 
     * @param code
     * @return
     */
    public static UnionpayAcctStatementFileEnum findByCode(String code) {
        for (UnionpayAcctStatementFileEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
