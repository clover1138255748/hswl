package com.opengroup.middleware.event.exception;

import com.opengroup.exception.BaseFrameworkException;

/**
 * ��Ϣ�м�����쳣
 * 
 * @author ruimin.jrm
 * @version $Id: UniformEventException.java, v 0.1 2015��6��4�� ����8:31:46 ruimin.jrm Exp $
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
