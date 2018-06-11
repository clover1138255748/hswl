package com.opengroup.fund.enums.unionpay;

/**
 * 借贷符号
 * 
 * @author laoniu
 * @version $Id: UnionpayAmountXEnum.java, v 0.1 2016年5月12日 下午2:44:18 laoniu Exp $
 */
public enum UnionpayAmountXEnum {

    C("C", "贷记"),

    D("D", "借记");

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

    private UnionpayAmountXEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static UnionpayAmountXEnum findByCode(String code) {
        for (UnionpayAmountXEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
