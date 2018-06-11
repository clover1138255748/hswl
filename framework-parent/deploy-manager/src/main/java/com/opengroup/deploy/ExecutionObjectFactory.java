/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy;

import java.util.Map;

/**
 * 
 * @author UU
 * @version $Id: ExecutionObjectFactory.java, v 0.1 2017年1月11日 下午5:45:03 UU Exp $
 */
public interface ExecutionObjectFactory<T> {

    ExecutionObject<T> createExecutionObject(String name, Map<String, Object> attributes);
}
