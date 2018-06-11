/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.wl.enums;

/**
 * ����������ID ö��
 * 
 * @author Gushu
 * @version $Id: WfSpecialIdEnum.java, v 0.1 2017��5��9�� ����11:05:13 Gushu Exp $
 */
public enum WfSpecialIdEnum {

    /**
     * �����Ĺ�����ID
     */
    budan_no_workflow(1, "budan_no_workflow", "�����Ĺ�����ID");

    private int    code;
    private String name;
    private String desc;

    private WfSpecialIdEnum(int code, String name, String desc) {
        setCode(code);
        setName(name);
        setDesc(desc);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
