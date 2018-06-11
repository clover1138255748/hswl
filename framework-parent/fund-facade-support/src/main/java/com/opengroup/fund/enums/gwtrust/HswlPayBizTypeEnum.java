package com.opengroup.fund.enums.gwtrust;

/**
 * ��ʨ����֧����ҵ������
 * 
 * @author laoying
 * @version $Id: HswlPayBizTypeEnum.java, v 0.1 2017��5��8�� ����11:47:37 laoying Exp $
 */
public enum HswlPayBizTypeEnum {

    FIRST_PAY("FIRST_PAY", "�״�֧�����û��Ŀ���֧���˻������/��֤�����ã�ת��ʨ�������"),

    FILL_MORE("FILL_MORE", "�໹,��ʨ����������Ĳ����˻��û�ԭ�ȵ�֧���˻�"),

    FILL_LESS("FILL_LESS", "�ٲ�,�û��Ŀ���֧���˻������/��֤�����ã�ת��ʨ�������");

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