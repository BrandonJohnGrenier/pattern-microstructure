package fm.pattern.microstructure.exceptions;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = -7022235229824655548L;
	private final List<String> errors = new ArrayList<String>();

	public AuthorizationException() {

	}

	public AuthorizationException(String message) {
		super(message);
		this.errors.add(message);
	}

	public AuthorizationException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
