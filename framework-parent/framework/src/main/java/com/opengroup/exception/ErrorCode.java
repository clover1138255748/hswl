package com.opengroup.exception;

import java.io.Serializable;

/**
 * 错误类
 * 
 * @author jin
 * @version $Id: ErrorCode.java, v 0.1 2015年6月26日 下午2:53:15 jin Exp $
 */
public class ErrorCode implements Serializable {
    /**  */
    private static final long serialVersionUID = -8629892800985305591L;
    /**
     * 系统
     */
    private String            system;
    /**
     * 错误码
     */
    private String            errorCode;
    /**
     * 错误消息
     */
    private String            msg;
    /**
     * 是否严重错误 
     */
    private boolean           serious;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSerious() {
        return serious;
    }

    public void setSerious(boolean serious) {
        this.serious = serious;
    }

}
