package cn.edu.tsinghua.academic.c00740273.magictower.engine;

@SuppressWarnings("serial")
public class GameSuccessTerminationException extends GameTerminationException {

	public GameSuccessTerminationException() {
		super();
	}

	public GameSuccessTerminationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GameSuccessTerminationException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameSuccessTerminationException(String message) {
		super(message);
	}

	public GameSuccessTerminationException(Throwable cause) {
		super(cause);
	}

}
