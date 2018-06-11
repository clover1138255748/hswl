/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy;

/**
 * 
 * @author UU
 * @version $Id: Handler.java, v 0.1 2017年1月11日 下午5:45:38 UU Exp $
 */
public interface Handler {

    /**
     * 命令名称
     * @return
     */
    public String getName();

    /**
     * 命令处理
     * @param context
     */
    public void handle(Context context, String param);
}
