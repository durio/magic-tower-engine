package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Event;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameTerminationException;

public class StandardGameTerminationException extends GameTerminationException {

	private static final long serialVersionUID = 1L;
	protected AttributeCheck check;

	public StandardGameTerminationException() {
	}

	public StandardGameTerminationException(String message) {
		super(message);
	}

	public StandardGameTerminationException(Throwable cause) {
		super(cause);
	}

	public StandardGameTerminationException(String message, Throwable cause) {
		super(message, cause);
	}

	public StandardGameTerminationException(String message, Event event) {
		super(message, event);
	}

	public StandardGameTerminationException(String message, Event event,
			AttributeCheck check) {
		super(message, event);
		this.check = check;
	}

	public AttributeCheck getAttributeCheck() {
		return this.check;
	}

}
