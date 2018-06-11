package com.opengroup.common.enums;

/**
 * ΢��ģ��ö��
 * @author mingzhou.lu
 * @version $Id: WeiXinEnum.java, v 0.1 2017��8��23�� ����6:43:31 mingzhou.lu Exp $
 */
public enum WeiXinTemplateEnum {
    /**
     * ����ģ����Ϣ
     */
    SUBSCIBE_TEMPLATE_MESSAGE(0, "7hhsp3uOwWRV3K0998cLE6y9q9w7v8XBm5SnSYw4JTc", "����ģ����Ϣ"),
    /**
     * ��Դ��Ϣ����
     */
    WAYBILL_CREATE(1, "OdEnsSWXF9VFJqqjgcQFbBjZxrbWG6X1X2iTMcF8zJI", "�˵�����"), GOODS_MESSAGE_REMIND(
                                                                                                   2,
                                                                                                   "UXgaWPg26-kwulLD6Zf0WQC1mJ7FpKjFzgGLyBW0kZA",
                                                                                                   "��Դ��Ϣ����"),
    /**
     * �����ύ�ɹ�֪ͨ
     */
    ORDER_SUBMIT_ADVICE(3, "nF523AI0o0Wvv_t_hNYm8riRsxANfZzTuWPF23yuJaI", "�����ύ�ɹ�֪ͨ"),
    /**
     * ����״̬֪ͨ
     */
    ORDER_STATUS_ADVICE(4, "vjeROy86MX9EmdLxDWYGgbYoDSfWKUQIqQd5jp86vgY", "����״̬֪ͨ"),
    /**
     * ��������
     */
    SEND_GOODS_ADVICE(5, "16nNc2pB7bplOs-Mb9w8hwu_JJILpsPCyEnVcksX2lM", "��������"),
    /**
     * �ӵ���Ϣ���ѣ�������˾��app��С����
     */
    SEND_ORDER_ADVICE(6, "GkMjf2jZRXugJ3Kyaamu4HR-CXQICqqnAm-J6rUOIXg", "�ӵ���Ϣ����");

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