package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Event;

public class StandardGameSuccessTerminationException extends
		StandardGameTerminationException {

	private static final long serialVersionUID = 1L;

	public StandardGameSuccessTerminationException() {
	}

	public StandardGameSuccessTerminationException(String message) {
		super(message);
	}

	public StandardGameSuccessTerminationException(Throwable cause) {
		super(cause);
	}

	public StandardGameSuccessTerminationException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public StandardGameSuccessTerminationException(String message, Event event) {
		super(message, event);
	}

	public StandardGameSuccessTerminationException(String message, Event event,
			AttributeCheck check) {
		super(message, event, check);
	}

}
