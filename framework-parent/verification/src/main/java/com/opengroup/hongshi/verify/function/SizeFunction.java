/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.function;

import java.util.Collection;
import java.util.Map;

import com.opengroup.hongshi.verify.Function;

/**
 * 
 * @author UU
 * @version $Id: SizeFunction.java, v 0.1 2017年1月6日 上午11:30:48 UU Exp $
 */
public class SizeFunction implements Function {

    /** 
     * @see com.opengroup.hongshi.verify.Function#getName()
     */
    @Override
    public String getName() {
        return "size";
    }

    /** 
     * @see com.opengroup.hongshi.verify.Function#handle(java.lang.Object[])
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Object handle(Object... data) {
        if (data != null && data.length > 0) {
            if (data[0] instanceof String) {
                return data[0].toString().length();
            } else if (data[0] instanceof Collection) {
                return ((Collection) data[0]).size();
            } else if (data[0] instanceof Map) {
                return ((Map) data[0]).size();
            } else if (data[0].getClass().isArray()) {
                return ((Object[]) data[0]).length;
            }
        }
        return -1;
    }

}
