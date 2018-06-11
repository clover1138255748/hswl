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
 * @version $Id: VerificationFactory.java, v 0.1 2016��12��21�� ����2:11:33 UU Exp $
 */
public interface VerificationFactory {

    /**
     * ������֤������֤����
     * 
     * @param name  verification������
     * @param data
     */
    void verify(String name, Object[] data);

    /**
     * ������֤��
     * 
     * @param url
     * @return
     */
    Map<String, Rule> build(URL[] urls);

}
