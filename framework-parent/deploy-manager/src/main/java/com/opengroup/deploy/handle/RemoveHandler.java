/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy.handle;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.opengroup.deploy.Context;
import com.opengroup.deploy.ExecutionObject;
import com.opengroup.deploy.Handler;
import com.opengroup.deploy.util.Toolers;
import com.opengroup.tools.string.StringUtil;

/**
 * 
 * @author UU
 * @version $Id: RemoveHandler.java, v 0.1 2017年1月11日 下午5:54:21 UU Exp $
 */
public class RemoveHandler implements Handler {

    /**
     * 添加的应用名称（必须）
     */
    public static final String NAME = "name";

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public void handle(Context context, String param) {
        if (!StringUtil.isBlank(param)) {
            Map<String, Object> attributes = Toolers.getSplitValue(param);
            Object oname = attributes.get(NAME);
            if (oname != null) {
                ExecutionObject<?> eo = context.getExecution((String) oname);
                eo.stop();
                context.removeExecution(eo);
                return;
            }
        }
        Set<ExecutionObject<?>> setEO = context.getExecutions();
        Iterator<ExecutionObject<?>> ieos = setEO.iterator();
        while (ieos.hasNext()) {
            ExecutionObject<?> eo = ieos.next();
            eo.stop();
            ieos.remove();
        }
    }

}
