package com.opengroup.exception;

public class UserKickException extends BaseFrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8568756497468418891L;

	public UserKickException() {
		super();
	}

	public UserKickException(String msg) {
		super(msg);
	}

	public UserKickException(Throwable t, String msg) {
		super(t, msg);
	}

	public UserKickException(Throwable t) {
		super(t);
	}

}
