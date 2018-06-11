/**
  * hongshiwl.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.opengroup.hongshi.verify.rule;

/**
 * ��Ĺ���
 * @author UU
 * @version $Id: OrHandler.java, v 0.1 2016��12��22�� ����11:53:37 UU Exp $
 */
public class OrRule extends AbstractMixRule {

    /**
     * @param id
     */
    public OrRule(String id) {
        super(id);
    }

    /** 
     * @see com.opengroup.hongshi.verify.rule.AbstractMixRule#handleResult(boolean, boolean)
     */
    @Override
    protected boolean mixHandle(boolean source, boolean target) {
        return source || target;
    }

}
