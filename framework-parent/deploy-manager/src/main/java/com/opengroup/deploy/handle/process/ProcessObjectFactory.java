/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy.handle.process;

import java.util.Map;

import com.opengroup.deploy.ExecutionObject;
import com.opengroup.deploy.ExecutionObjectFactory;

/**
 * 
 * @author UU
 * @version $Id: ProcessObjectFactory.java, v 0.1 2017年1月11日 下午5:57:02 UU Exp $
 */
public class ProcessObjectFactory implements ExecutionObjectFactory<ProcessBuilder> {

    @Override
    public ExecutionObject<ProcessBuilder> createExecutionObject(String name,
                                                                 Map<String, Object> attributes) {
        return new ProcessObject(name, attributes);
    }

}
