package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.DataException;

@SuppressWarnings("serial")
public class DataFormatException extends DataException {

	public DataFormatException() {
	}

	public DataFormatException(String message) {
		super(message);
	}

	public DataFormatException(Throwable cause) {
		super(cause);
	}

	public DataFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
