package fm.pattern.validation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InternalErrorException extends RuntimeException {

	private static final long serialVersionUID = -7093595345324626648L;
	private final List<String> errors = new ArrayList<String>();

	public InternalErrorException() {

	}

	public InternalErrorException(String message) {
		super(message);
		this.errors.add(message);
	}

	public InternalErrorException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
