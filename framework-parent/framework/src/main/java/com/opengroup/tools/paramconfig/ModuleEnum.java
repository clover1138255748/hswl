package com.opengroup.tools.paramconfig;

/**
 * 模块枚举
 * @author mingzhou.lu
 * @version $Id: ModuleEnum.java, v 0.1 2017年7月10日 上午11:32:23  mingzhou.lu Exp $
 */
public enum ModuleEnum {

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
     * 财务
     */
    FINANCIAL(10, "financial"),
    /**
     * 业务用户
     */
    UIC(11, "uic"),
    /**
     * 工单系统
     */
    WO(12, "wo"),
    /**
     * 用户管理
     */
    USERMANAGER(13, "usermanager"),
    /**
     * 工作流引擎
     */
    WF(13, "wf"),
    /**
     * 资金用户
     */
    CIF(14, "cif"),
    /**
     * 交易员功能
     */
    TRADEGUIDE(15, "tradeguide"),
    /**
     *消息中心
     */
    MSGCENTER(16, "msgcenter"),
    /**
     *基础服务
     */
    COMMINSERIVCE(17, "commonservice"),
    /**
     *基础框架
     */
    FRAMEWORK(18, "framework"),
    /**
     *应收管理
     */
    RECEIPT(19, "receipt"),
    /**
     *承运模块
     */
    CARRIER(20, "carrier"),
    GOODSOWNER(21, "goodsowner"),
    GWWWW(22, "gwwww"),
    RELEASE(23, "release"),
    GWAPP(24, "gwapp");

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

    private ModuleEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static ModuleEnum parse(int value) {
        for (ModuleEnum item : ModuleEnum.values()) {
            if (value == item.value) {
                return item;
            }
        }
        return null;
    }
}
