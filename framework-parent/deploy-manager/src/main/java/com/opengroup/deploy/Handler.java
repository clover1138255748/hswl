/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy;

/**
 * 
 * @author UU
 * @version $Id: Handler.java, v 0.1 2017��1��11�� ����5:45:38 UU Exp $
 */
public interface Handler {

    /**
     * ��������
     * @return
     */
    public String getName();

    /**
     * �����
     * @param context
     */
    public void handle(Context context, String param);
}
