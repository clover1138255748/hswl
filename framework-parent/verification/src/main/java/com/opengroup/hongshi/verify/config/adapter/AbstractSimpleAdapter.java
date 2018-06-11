/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.config.adapter;

import com.opengroup.hongshi.verify.MateData;
import com.opengroup.hongshi.verify.VerificationConfigException;

/**
 * 
 * @author UU
 * @version $Id: AbstractSimpleAdapter.java, v 0.1 2017��1��6�� ����2:35:17 UU Exp $
 */
public abstract class AbstractSimpleAdapter extends AbstractAdapter {

    protected MateData getSourceMateData(String str) {
        MateData md = tooler.buildSingleMateData(str);
        if (md == null) {
            throw new VerificationConfigException("����" + getAdapterName() + "ʱ������sourceΪ��");
        }
        return md;
    }

}
