package gap.client.exception;

public class EmptyInputException extends Exception {
	public EmptyInputException() {
		super();
	}

	public EmptyInputException(String mess) {
		super(mess);
	}
}
