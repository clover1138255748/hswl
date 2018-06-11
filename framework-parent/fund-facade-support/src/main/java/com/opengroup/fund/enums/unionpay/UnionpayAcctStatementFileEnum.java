package com.opengroup.fund.enums.unionpay;

/**
 * 银联商户获取文件类型
 * 
 * @author laoniu
 * @version $Id: UnionpayAcctStatementFileEnum.java, v 0.1 2016年4月26日 上午11:36:56 laoniu Exp $
 */
public enum UnionpayAcctStatementFileEnum {

    /**
     * 商户号（ 15 位） _日期(YYYYMMDD).zip
     */
    TRANSACTION_SERIAL("00", "交易流水文件", "zip"),

    /**
     * CUPMBCC_Remittance_Daily_Report_日期(YYYYMMDD).csv
     */
    CUPMBCC_Remittance_Daily_Report("81", "汇款日汇总", "csv"),

    /**
     * CUPMBCC_Settlement_Daily_Report_日期(YYYYMMDD).csv
     */
    CUPMBCC_Settlement_Daily_Report("82", "结算日汇总", "csv"),

    /**
     * CUPMBCC_Settlement_Monthly_Report_月份(YYYYMM)01.csv
     */
    CUPMBCC_Settlement_Monthly_Report("83", "结算月汇总", "csv"),

    /**
     * CUPMBCC_Chargeback_Daily_Report_日期(YYYYMMDD).csv
     */
    CUPMBCC_Chargeback_Daily_Report("84", "扣费日汇总", "csv"),

    /**
     * 收单机构号（ 8 位） _商户号(15 位)_out.tar.gz
     */
    TRADE_SERIAL("85", "收单流水文件", "tar.gz"),

    /**
     * CUPS_商户号（ 15 位） _清算日期(YYYYMMDD)
     */
    CUPS_SERIAL("87", "CUPS流水文件", ""),

    /**
     * MC 商户号(后 8 位)清算日期(YYMMDD)_GRP
     */
    MC_GRP("88", "MC_GRP", ""),

    /**
     * MC 商户号(后 8 位)清算日期(YYMMDD)
     */
    MC("89", "MC", ""),

    /**
     * MC 商户号(后 8 位)清算日期(YYMMDD)_GNETE
     */
    MC_GNETE("90", "MC_GNETE", ""),

    /**
     * WKDS 商户号（ 15 位）清算日期（ YYMMDD）
     */
    WKDS("91", "WKDS", "");

    /**
     * 枚举的code
     */
    private String code;
    /**
     * 描述
     */
    private String description;

    /**
     * 文件结尾
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
     * 通过CODE查找枚举
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
