package fm.pattern.validation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -7022235345324655548L;
	private final List<String> errors = new ArrayList<String>();

	public AuthenticationException() {

	}

	public AuthenticationException(String message) {
		super(message);
		this.errors.add(message);
	}

	public AuthenticationException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
