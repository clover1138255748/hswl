package com.opengroup.middleware.event;

public enum UniformEventTypeEnum {
    /**
     * jvm�ڲ���ͬ����Ϣ
     */
    JVM_SYNC("JVM_SYNC", "jvm�ڲ���ͬ����Ϣ"),

    /**
     * jvm�ڲ����첽����
     */
    JVM_ASYN("JVM_ASYN", "jvm�ڲ����첽����");

    /**
     * ����
     */
    private String code;
    /**
     * ����
     */
    private String desc;

    private UniformEventTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * ͨ��code����ö��
     * 
     * @param code
     * @return
     */
    public static UniformEventTypeEnum findByCode(String code) {
        for (UniformEventTypeEnum item : UniformEventTypeEnum.values()) {
            if (item.getCode().equals(code))
                return item;
        }
        return null;
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

}
