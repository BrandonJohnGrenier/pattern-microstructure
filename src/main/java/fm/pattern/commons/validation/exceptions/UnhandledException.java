package fm.pattern.commons.validation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class UnhandledException extends RuntimeException {

	private static final long serialVersionUID = -7093595345324626648L;
	private final List<String> errors = new ArrayList<String>();

	public UnhandledException() {

	}

	public UnhandledException(String message) {
		super(message);
		this.errors.add(message);
	}

	public UnhandledException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
