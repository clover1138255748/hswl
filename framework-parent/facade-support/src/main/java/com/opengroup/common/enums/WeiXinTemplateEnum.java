package com.opengroup.common.enums;

/**
 * 微信模版枚举
 * @author mingzhou.lu
 * @version $Id: WeiXinEnum.java, v 0.1 2017年8月23日 下午6:43:31 mingzhou.lu Exp $
 */
public enum WeiXinTemplateEnum {
    /**
     * 订阅模板消息
     */
    SUBSCIBE_TEMPLATE_MESSAGE(0, "7hhsp3uOwWRV3K0998cLE6y9q9w7v8XBm5SnSYw4JTc", "订阅模板消息"),
    /**
     * 货源信息提醒
     */
    WAYBILL_CREATE(1, "OdEnsSWXF9VFJqqjgcQFbBjZxrbWG6X1X2iTMcF8zJI", "运单生成"), GOODS_MESSAGE_REMIND(
                                                                                                   2,
                                                                                                   "UXgaWPg26-kwulLD6Zf0WQC1mJ7FpKjFzgGLyBW0kZA",
                                                                                                   "货源信息提醒"),
    /**
     * 订单提交成功通知
     */
    ORDER_SUBMIT_ADVICE(3, "nF523AI0o0Wvv_t_hNYm8riRsxANfZzTuWPF23yuJaI", "订单提交成功通知"),
    /**
     * 订单状态通知
     */
    ORDER_STATUS_ADVICE(4, "vjeROy86MX9EmdLxDWYGgbYoDSfWKUQIqQd5jp86vgY", "订单状态通知"),
    /**
     * 发货提醒
     */
    SEND_GOODS_ADVICE(5, "16nNc2pB7bplOs-Mb9w8hwu_JJILpsPCyEnVcksX2lM", "发货提醒"),
    /**
     * 接单消息提醒（车主、司机app、小程序）
     */
    SEND_ORDER_ADVICE(6, "GkMjf2jZRXugJ3Kyaamu4HR-CXQICqqnAm-J6rUOIXg", "接单消息提醒");

    private int    code;
    private String templateId;
    private String remark;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private WeiXinTemplateEnum(int code, String templateId, String remark) {
        this.code = code;
        this.templateId = templateId;
        this.remark = remark;
    }

}