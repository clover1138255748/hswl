/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.wl.enums;

/**
 * 工作流特殊ID 枚举
 * 
 * @author Gushu
 * @version $Id: WfSpecialIdEnum.java, v 0.1 2017年5月9日 上午11:05:13 Gushu Exp $
 */
public enum WfSpecialIdEnum {

    /**
     * 补单的工作流ID
     */
    budan_no_workflow(1, "budan_no_workflow", "补单的工作流ID");

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
