/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author UU
 * @version $Id: DefaultAdapterFactory.java, v 0.1 2017年1月6日 下午4:30:33 UU Exp $
 */
public class DefaultAdapterFactory implements AdapterFactory {

    /** 
     * @see com.opengroup.hongshi.verify.config.AdapterFactory#getAdapter(java.lang.String)
     */
    @Override
    public Adapter getAdapter(String name) {
        return (Adapter) adapters.get(name);
    }

    private Map<String, Adapter> adapters = new HashMap<String, Adapter>();

    public void setAdapters(List<Adapter> adapters) {
        for (Adapter adapter : adapters) {
            this.adapters.put(adapter.getAdapterName(), adapter);
        }
    }
}
