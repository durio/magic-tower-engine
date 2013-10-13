package cn.edu.tsinghua.academic.c00740273.magictower.engine;

@SuppressWarnings("serial")
public class GameFailureTerminationException extends GameTerminationException {

	public GameFailureTerminationException() {
		super();
	}

	public GameFailureTerminationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GameFailureTerminationException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameFailureTerminationException(String message) {
		super(message);
	}

	public GameFailureTerminationException(Throwable cause) {
		super(cause);
	}

	public GameFailureTerminationException(String message, Event event) {
		super(message, event);
	}

}
