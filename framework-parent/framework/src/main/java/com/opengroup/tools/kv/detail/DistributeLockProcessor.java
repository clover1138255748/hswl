/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.tools.kv.detail;

/**
 * 
 * @author jin
 * @version $Id: DistributeLockProcessor.java, v 0.1 2015年9月25日 下午6:55:53 jin Exp $
 */
public interface DistributeLockProcessor<T> {

    T process() throws Exception;
}
