/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.opengroup.exception;

/**
 * 
 * @author jin
 * @version $Id: WaitTimeoutException.java, v 0.1 2015年9月25日 下午8:31:27 jin Exp $
 */
public class WaitTimeoutException extends BaseFrameworkException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2571609199859576305L;

	/**
     * Override constructor from Error.
     *
     * @see RuntimeException
     */
    public WaitTimeoutException() {
    }

    /**
     * Override constructor from Error.
     *
     * @param message passed to Error
     * @see RuntimeException
     */
    public WaitTimeoutException(String message) {
        super(message);
    }

    /**
     * Override constructor from Error.
     *
     * @param message passed to Error
     * @param cause   passed to Error
     * @see Error
     */
    public WaitTimeoutException(String message, Throwable cause) {
        super(cause, message);
    }

    /**
     * Override constructor from Error.
     *
     * @param cause passed to Error
     * @see Error
     */
    public WaitTimeoutException(Throwable cause) {
        super(cause);
    }
}
