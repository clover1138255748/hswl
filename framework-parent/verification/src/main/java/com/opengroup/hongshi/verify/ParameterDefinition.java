/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify;

/**
 * 
 * @author UU
 * @version $Id: Paramter.java, v 0.1 2016年12月28日 下午3:10:11 UU Exp $
 */
public class ParameterDefinition {

    private ExpressionType parameterType;

    private Object         value;

    /**
     * Getter method for property <tt>parameterType</tt>.
     * 
     * @return property value of parameterType
     */
    public ExpressionType getParameterType() {
        return parameterType;
    }

    /**
     * Setter method for property <tt>parameterType</tt>.
     * 
     * @param parameterType value to be assigned to property parameterType
     */
    public void setParameterType(ExpressionType parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * Getter method for property <tt>value</tt>.
     * 
     * @return property value of value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     * 
     * @param value value to be assigned to property value
     */
    public void setValue(Object value) {
        this.value = value;
    }

}
