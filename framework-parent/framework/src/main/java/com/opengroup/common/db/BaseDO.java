package com.opengroup.common.db;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础 的DO
 * 
 * @author jin
 * @version $Id: BaseDO.java, v 0.1 2016年3月29日 上午11:37:26 jin Exp $
 */
public abstract class BaseDO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7857073998611074946L;

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
