package hust.soict.globalict.aims.exception;

public class DuplicatedItemException extends Exception {

	public DuplicatedItemException() {
	}

	public DuplicatedItemException(String message) {
		super(message);
	}

	public DuplicatedItemException(Throwable cause) {
		super(cause);
	}

	public DuplicatedItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedItemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
