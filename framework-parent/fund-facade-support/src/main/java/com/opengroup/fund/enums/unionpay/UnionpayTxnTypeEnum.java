package com.opengroup.fund.enums.unionpay;

/**
 * 交易类型
 * 
 * @author laoniu
 * @version $Id: UnionpayPayTxnTypeEnum.java, v 0.1 2016年4月26日 下午4:15:13 laoniu Exp $
 */
public enum UnionpayTxnTypeEnum {

    QUERY("00", "查询交易"),

    CONSUME("01", "消费"),

    PRE_AUTH("02", "预授权"),

    PRE_AUTH_FINISHED("03", "预授权完成"),

    RETURN_GOODS("04", "退款"),

    /**
     * credit for load。圈提 debit for load。
     */
    CREDIT_FOR_LOAD("05", "圈存"),

    COLLECTING_FOR_ANOTHER_AGENCY("11", "代收"),

    PAY_FOR_ANOTHER("12", "代付"),

    BILL_PAY("13", "账单支付"),

    TRANSFER_ACCOUNTS("14", "转账（保留）"),

    BATCH_TRANSACTIONS("21", "批量交易"),

    BATCH_QUERY("22", "批量查询"),

    CONSUME_CANCEL("31", "消费撤销"),

    PRE_AUTH_CANCEL("32", "预授权撤销"),

    PRE_AUTH_FINISHED_CANCEL("33", "预授权完成撤销"),

    BALANCE_QUERY("71", "余额查询"),

    CERTIFICATION_AND_BIND("72", "实名认证-建立绑定关系"),

    ACCOUNT_STATEMENT_QUERY("73", "账单查询 "),

    UNBIND("74", "解除绑定关系"),

    BIND_QUERY("75", "查询绑定关系"),

    SEND_SMS_CODE("77", "发送短信验证码交易"),

    OPEN_QUERY_TRANSACTIONS("78", "开通查询交易"),

    OPEN_TRANSACTIONS("79", "开通交易"),

    IC_CARD_SHELL_NOTICE("94", "IC 卡脚本通知");

    /**
     * 枚举的code
     */
    private String code;
    /**
     * 描述
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
     * 通过CODE查找枚举
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
