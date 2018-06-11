package com.opengroup.middleware.event.exception;

import com.opengroup.exception.BaseFrameworkException;

/**
 * 消息中间件的异常
 * 
 * @author ruimin.jrm
 * @version $Id: UniformEventException.java, v 0.1 2015年6月4日 下午8:31:46 ruimin.jrm Exp $
 */
public class UniformEventException extends BaseFrameworkException {

    /**  */
    private static final long serialVersionUID = 583361705567830745L;

    public UniformEventException() {
        super();
    }

    public UniformEventException(String msg) {
        super(msg);
    }

    public UniformEventException(Throwable t, String msg) {
        super(t, msg);
    }

    public UniformEventException(Throwable t) {
        super(t);
    }
}
