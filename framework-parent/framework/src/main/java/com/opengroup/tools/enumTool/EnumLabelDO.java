/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.enumTool;

/**
 * 
 * @author jin
 * @version $Id: EnumLabelDO.java, v 0.1 2015��8��21�� ����5:43:55 jin Exp $
 */
public class EnumLabelDO {

    /**
     * ID,һ����ö�ٵ�����+������ΪΨһֵ
     */
    private String className;
    /**
     * 
     */
    private String enumName;
    /**
     * LABEL,��Ҫ��ʾ��Txt
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
