package com.opengroup.middleware.event;

public enum UniformEventTypeEnum {
    /**
     * jvm内部的同步消息
     */
    JVM_SYNC("JVM_SYNC", "jvm内部的同步消息"),

    /**
     * jvm内部的异步机制
     */
    JVM_ASYN("JVM_ASYN", "jvm内部的异步机制");

    /**
     * 代码
     */
    private String code;
    /**
     * 描述
     */
    private String desc;

    private UniformEventTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过code查找枚举
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
