package com.queencastle.service.exceptions;

/**
 * 业务层异常
 * 
 * @author yudongwei
 *
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1171341427094100623L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
