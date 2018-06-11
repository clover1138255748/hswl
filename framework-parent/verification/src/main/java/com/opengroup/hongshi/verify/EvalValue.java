/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify;

/**
 * 
 * @author UU
 * @version $Id: EvalValue.java, v 0.1 2017年1月6日 下午12:09:37 UU Exp $
 */
public class EvalValue {

    private int    site;

    private String expression;

    public EvalValue() {

    }

    public EvalValue(int site, String expression) {
        this.site = site;
        this.expression = expression;
    }

    /**
     * Getter method for property <tt>site</tt>.
     * 
     * @return property value of site
     */
    public int getSite() {
        return site;
    }

    /**
     * Setter method for property <tt>site</tt>.
     * 
     * @param site value to be assigned to property site
     */
    public void setSite(int site) {
        this.site = site;
    }

    /**
     * Getter method for property <tt>expression</tt>.
     * 
     * @return property value of expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Setter method for property <tt>expression</tt>.
     * 
     * @param expression value to be assigned to property expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder('$').append(site);
        return expression == null || expression.length() == 0 ? buffer.toString()
            : buffer.append('.').append(expression).toString();
    }

}
