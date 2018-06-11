package com.opengroup.tools.paramconfig;

/**
 * 网关枚举
 * @author mingzhou.lu
 * @version $Id: GatewayEnum.java, v 0.1 2017年7月10日 上午11:32:11  mingzhou.lu Exp $
 */
public enum GatewayEnum {

    /**
     * 报表网关
     */
    GWRP(1, "gwrp"),
    /**
     * 司机网关
     */
    GSEJ(2, "gwsj"),
    /**
     * 资金可信网关
     */
    GWTRUST(3, "gwtrust"),
    /**
     * 车主网关
     */
    GWCZ(4, "gwcz"),
    /**
     * 货主网关
     */
    GWHZ(5, "gwhz"),
    /**
     * 货运站网关
     */
    GWHYZ(6, "gwhyz"),
    /**
     * 小二
     */
    GWCRM(7, "gwcrm"),
    /**
     * 第三方网关
     */
    GWTHIRDPT(8, "gwthirdpt"),
    /**
     * 单点登录网关
     */
    GWLOGIN(9, "gwlogin"),
    /**
     * 站点网关
     */
    GWWWW(10, "gwwww"),
    /**
     * 运维统一发版
     */
    RELEASE(11, "release"),
    GWAPP(12, "gwapp");

    private int    value;

    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private GatewayEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static GatewayEnum parse(int value) {
        for (GatewayEnum item : GatewayEnum.values()) {
            if (value == item.value) {
                return item;
            }
        }
        return null;
    }
}
