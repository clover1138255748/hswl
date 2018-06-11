package com.opengroup.fund.dto;

import java.io.Serializable;

public class EventTradeDTO implements Serializable {

    private static final long serialVersionUID = -7075949330224578383L;

    /**
     * 收单id
     */
    private String            tradeId;

    /**
     * 收单结果，目前有：CONFIRM_PAYED、FAIL、CANCEL
     */
    private String            status;

    /**
     * 银行流水号
     */
    private String            bankSerialNo;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo;
    }

}
