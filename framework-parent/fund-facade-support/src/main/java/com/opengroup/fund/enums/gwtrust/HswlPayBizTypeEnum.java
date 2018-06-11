package com.opengroup.fund.enums.gwtrust;

/**
 * 红狮物流支付的业务类型
 * 
 * @author laoying
 * @version $Id: HswlPayBizTypeEnum.java, v 0.1 2017年5月8日 上午11:47:37 laoying Exp $
 */
public enum HswlPayBizTypeEnum {

    FIRST_PAY("FIRST_PAY", "首次支付，用户的可用支付账户（余额/保证金，信用）转红狮物流余额"),

    FILL_MORE("FILL_MORE", "多还,红狮物流余额将多余的部分退回用户原先的支付账户"),

    FILL_LESS("FILL_LESS", "少补,用户的可用支付账户（余额/保证金，信用）转红狮物流余额");

    private String code;

    private String desc;

    private HswlPayBizTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static HswlPayBizTypeEnum findByCode(String code) {
        for (HswlPayBizTypeEnum item : HswlPayBizTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}