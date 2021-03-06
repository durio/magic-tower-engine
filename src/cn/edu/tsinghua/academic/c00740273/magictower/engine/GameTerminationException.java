package cn.edu.tsinghua.academic.c00740273.magictower.engine;

@SuppressWarnings("serial")
public class GameTerminationException extends Exception {

	protected Event event;

	public GameTerminationException() {
		super();
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

	public GameTerminationException(String message, Event event) {
		super(message);
		this.event = event;
	}

	public Event getEvent() {
		return this.event;
	}

}
