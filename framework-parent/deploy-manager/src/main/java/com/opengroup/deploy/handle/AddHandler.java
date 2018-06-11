/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy.handle;

import java.util.Map;

import com.opengroup.deploy.Context;
import com.opengroup.deploy.ExecutionObject;
import com.opengroup.deploy.ExecutionObjectFactory;
import com.opengroup.deploy.Handler;
import com.opengroup.deploy.ParamException;
import com.opengroup.deploy.util.Toolers;
import com.opengroup.tools.string.StringUtil;
import com.opengroup.tools.system.AdaptiveLoader;

/**
 * 
 * @author UU
 * @version $Id: AddHandler.java, v 0.1 2017年1月11日 下午5:50:18 UU Exp $
 */
public class AddHandler implements Handler {
    /**
     * 添加的应用名称（必须）
     */
    public static final String NAME         = "name";

    /**
     * 默认加载的文件
     */
    public static final String DEFAULT_PATH = "classpath*:application.xml";

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void handle(Context context, String param) {
        if (StringUtil.isBlank(param)) {
            throw new ParamException("add命令必须要有name参数");
        }

        Map<String, Object> attributes = Toolers.getSplitValue(param);

        Object oname = attributes.get(NAME);
        if (oname == null || StringUtil.isBlank((String) oname)) {
            throw new ParamException("add命令必须要有name参数");
        }
        ExecutionObjectFactory<?> factory = AdaptiveLoader
            .getAdaptiveLoader(ExecutionObjectFactory.class).getAdapter(type);
        ExecutionObject<?> eo = factory.createExecutionObject((String) oname, attributes);
        context.addExecution(eo);
    }

    private String type;

    public void setType(String type) {
        this.type = type;
    }
}
