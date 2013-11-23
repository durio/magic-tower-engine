package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Event;

public class StandardGameFailureTerminationException extends
		StandardGameTerminationException {

	private static final long serialVersionUID = 1L;

	public StandardGameFailureTerminationException() {
		super();
	}

	public StandardGameFailureTerminationException(String message) {
		super(message);
	}

	public StandardGameFailureTerminationException(Throwable cause) {
		super(cause);
	}

	public StandardGameFailureTerminationException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public StandardGameFailureTerminationException(String message, Event event) {
		super(message, event);
	}

	public StandardGameFailureTerminationException(String message, Event event,
			AttributeCheck check) {
		super(message, event, check);
	}

}
