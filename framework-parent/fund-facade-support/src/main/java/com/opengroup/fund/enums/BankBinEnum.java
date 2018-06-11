package com.opengroup.fund.enums;

import java.util.List;

import com.opengroup.common.bank.BankTypeEnum;

/**
 * 银行卡Bin的枚举工具类
 * 
 * @author laoniu
 * @version $Id: Snippet.java, v 0.1 2015年11月9日 上午11:12:34 laoniu Exp $
 */
public class BankBinEnum {
    private int              value;

    private BankTypeEnum     bankTypeEnum;

    private BankCardTypeEnum bankCardTypeEnum;

    private int              cardLength;

    private String           bin;

    public int getValue() {
        return value;
    }

    public BankTypeEnum getBankTypeEnum() {
        return bankTypeEnum;
    }

    public BankCardTypeEnum getBankCardTypeEnum() {
        return bankCardTypeEnum;
    }

    public int getCardLength() {
        return cardLength;
    }

    public String getBin() {
        return bin;
    }

    public BankBinEnum() {
    }

    private BankBinEnum(int value, BankTypeEnum bankTypeEnum, BankCardTypeEnum bankCardTypeEnum,
                        int cardLength, String bin) {
        this.value = value;
        this.bankTypeEnum = bankTypeEnum;
        this.bankCardTypeEnum = bankCardTypeEnum;
        this.cardLength = cardLength;
        this.bin = bin;
    }

    /**
     * 通过bin查找枚举
     * 
     * @param code
     * @return
     */
    public static BankBinEnum findByBin(String bin) {
        BankBinEnum result = null;
        BinEnum1[] bin1s = BinEnum1.values();
        for (BinEnum1 item : bin1s) {
            if (item.getBin().equals(bin)) {
                result = new BankBinEnum(item.getValue(), item.getBankTypeEnum(),
                    item.getBankCardTypeEnum(), item.getCardLength(), item.getBin());
                return result;
            }
        }

        BinEnum2[] bin2s = BinEnum2.values();
        for (BinEnum2 item : bin2s) {
            if (item.getBin().equals(bin)) {
                result = new BankBinEnum(item.getValue(), item.getBankTypeEnum(),
                    item.getBankCardTypeEnum(), item.getCardLength(), item.getBin());
                return result;
            }
        }
        return result;
    }

    /**
     * 通过bin查找枚举
     * 
     * @param code
     * @return
     */
    public static BankBinEnum findByBinAndCardLength(int cardLength, List<String> bins) {
        BankBinEnum result = null;

        BinEnum1[] bin1s = BinEnum1.values();
        for (BinEnum1 item : bin1s) {
            if (item.getCardLength() == cardLength) {
                if (bins.contains(item.getBin())) {
                    result = new BankBinEnum(item.getValue(), item.getBankTypeEnum(),
                        item.getBankCardTypeEnum(), item.getCardLength(), item.getBin());
                    return result;
                }
            }
        }
        BinEnum2[] bin2s = BinEnum2.values();
        for (BinEnum2 item : bin2s) {
            if (item.getCardLength() == cardLength) {
                if (bins.contains(item.getBin())) {
                    result = new BankBinEnum(item.getValue(), item.getBankTypeEnum(),
                        item.getBankCardTypeEnum(), item.getCardLength(), item.getBin());
                    return result;
                }
            }
        }
        return null;
    }

    public static BankBinEnum parse(int value) {
        BankBinEnum result = null;
        BinEnum1[] bin1s = BinEnum1.values();
        for (BinEnum1 item : bin1s) {
            if (value == item.getValue()) {
                result = new BankBinEnum(item.getValue(), item.getBankTypeEnum(),
                    item.getBankCardTypeEnum(), item.getCardLength(), item.getBin());
                return result;
            }
        }
        BinEnum2[] bin2s = BinEnum2.values();
        for (BinEnum2 item : bin2s) {
            if (value == item.getValue()) {
                result = new BankBinEnum(item.getValue(), item.getBankTypeEnum(),
                    item.getBankCardTypeEnum(), item.getCardLength(), item.getBin());
                return result;
            }
        }
        return result;
    }
}
