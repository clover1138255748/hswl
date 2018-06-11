package com.opengroup.fund.enums.unionpay;

/**
 * 银联渠道类型
 * 
 * @author laoniu
 * @version $Id: UnionpayChannelTypeEnum.java, v 0.1 2016年5月30日 上午10:31:13 laoniu Exp $
 */
public enum UnionpayChannelTypeEnum {
    VOICE("05", "语音"),

    INTERNET("07", "互联网"),

    MOBILE("08", "移动");

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

    private UnionpayChannelTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 通过CODE查找枚举
     * 
     * @param code
     * @return
     */
    public static UnionpayChannelTypeEnum findByCode(String code) {
        for (UnionpayChannelTypeEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
