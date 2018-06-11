/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.opengroup.common.support;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jin
 * @version $Id: BaseDTO.java, v 0.1 2016年5月11日 上午11:23:05 jin Exp $
 */
public class BaseDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = -4163359213681894507L;
    private String            id;
    private Date              gmtCreate;
    private Date              gmtModified;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
