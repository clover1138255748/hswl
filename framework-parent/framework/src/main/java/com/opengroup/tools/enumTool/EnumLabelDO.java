/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.enumTool;

/**
 * 
 * @author jin
 * @version $Id: EnumLabelDO.java, v 0.1 2015年8月21日 下午5:43:55 jin Exp $
 */
public class EnumLabelDO {

    /**
     * ID,一般以枚举的类名+名字作为唯一值
     */
    private String className;
    /**
     * 
     */
    private String enumName;
    /**
     * LABEL,需要显示的Txt
     */
    private String label;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
