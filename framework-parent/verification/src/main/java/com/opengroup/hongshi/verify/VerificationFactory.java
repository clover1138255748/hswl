/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify;

import java.net.URL;
import java.util.Map;

/**
 * 
 * @author UU
 * @version $Id: VerificationFactory.java, v 0.1 2016年12月21日 下午2:11:33 UU Exp $
 */
public interface VerificationFactory {

    /**
     * 根据验证名称验证数据
     * 
     * @param name  verification的名称
     * @param data
     */
    void verify(String name, Object[] data);

    /**
     * 构建验证器
     * 
     * @param url
     * @return
     */
    Map<String, Rule> build(URL[] urls);

}
