package cn.edu.tsinghua.academic.c00740273.magictower.engine;

@SuppressWarnings("serial")
public class GameTerminationException extends Exception {

	public GameTerminationException() {
		super();
	}

	public GameTerminationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GameTerminationException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameTerminationException(String message) {
		super(message);
	}

	public GameTerminationException(Throwable cause) {
		super(cause);
	}

}
