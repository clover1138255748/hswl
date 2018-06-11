/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.config.adapter;

import com.opengroup.hongshi.verify.config.AdapterFactory;

/**
 * 
 * @author UU
 * @version $Id: MixAbstractAdapter.java, v 0.1 2017年1月4日 下午3:20:53 UU Exp $
 */
public abstract class AbstractMultipleAdapter extends AbstractAdapter {

    protected AdapterFactory adapterFactory;

    public void setAdapterFactory(AdapterFactory adapterFactory) {
        this.adapterFactory = adapterFactory;
    }

    public AdapterFactory getAdapterFactory() {
        return adapterFactory;
    }
}
