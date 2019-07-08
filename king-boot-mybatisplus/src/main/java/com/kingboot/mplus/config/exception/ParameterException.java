package com.kingboot.mplus.config.exception;

/**
 * @author waylen.chi
 * @date 2018年06月22日16:22:15
 */
@SuppressWarnings ("serial")
public class ParameterException extends RuntimeException {
	public ParameterException(Throwable t) {
		super(t);
	}

	public ParameterException(String string) {
		super(string);
	}

	public ParameterException(String string, Throwable t) {
		super(string, t);
	}
}
