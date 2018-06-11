package com.opengroup.fund.enums.unionpay;

/**
 * 银联入库文件入库状态
 * 
 * @author laoniu
 * @version $Id: UnionpayLoadStatusEnum.java, v 0.1 2016年5月23日 上午11:11:26 laoniu Exp $
 */
public enum UnionpayLoadStatusEnum {
    SUCCESS("SUCCESS", "成功"),

    FAIL("FAIL", "失败");

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

    private UnionpayLoadStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static UnionpayLoadStatusEnum findByCode(String code) {
        for (UnionpayLoadStatusEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
