/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.kv.detail;

/**
 * 
 * @author jin
 * @version $Id: DistributeLockProcessor.java, v 0.1 2015��9��25�� ����6:55:53 jin Exp $
 */
public interface DistributeLockProcessor<T> {

    T process() throws Exception;
}
