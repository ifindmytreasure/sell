package com.imooc.o2o.exceptions;

/**
 * Created by Unruly Wind on 2019/3/13/013.
 *
 * @author BlueMelancholy
 * @desc:
 */
public class UserProductMapOperateException extends RuntimeException {
	private static final long serialVersionUID = 2047827992015281842L;

	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public UserProductMapOperateException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public UserProductMapOperateException(String message) {
		super(message);
	}
}
