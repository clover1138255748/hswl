/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verification.test.impl;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.opengroup.hongshi.verification.test.TestFacade;

/**
 * 
 * @author UU
 * @version $Id: TestServiceImpl.java, v 0.1 2017年1月12日 下午2:35:37 UU Exp $
 */
public class TestFacadeImpl implements TestFacade, ApplicationContextAware {

    /** 
     * @see verification.test.TestFacade#handle(java.util.Map)
     */
    @Override
    public void handle(Map<String, Object> params) {
        System.err.println(this.getClass().getName());
    }

    /** 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println(this.getClass().getName() + " : " + applicationContext.hashCode());
    }

}
