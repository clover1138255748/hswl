package com.opengroup.exception;

/**
 * 严重的错误，系统不能启动
 * 
 * @author ruimin.jrm
 * @version $Id: CriticalSystemError.java, v 0.1 2015年6月4日 下午7:58:03 ruimin.jrm Exp $
 */
public class CriticalSystemError extends Error {

    private static final long serialVersionUID = -5898511057957320305L;

    /**
     * Override constructor from Error.
     *
     * @see RuntimeException
     */
    public CriticalSystemError() {
    }

    /**
     * Override constructor from Error.
     *
     * @param message passed to Error
     * @see RuntimeException
     */
    public CriticalSystemError(String message) {
        super(message);
    }

    /**
     * Override constructor from Error.
     *
     * @param message passed to Error
     * @param cause   passed to Error
     * @see Error
     */
    public CriticalSystemError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Override constructor from Error.
     *
     * @param cause passed to Error
     * @see Error
     */
    public CriticalSystemError(Throwable cause) {
        super(cause);
    }
}
