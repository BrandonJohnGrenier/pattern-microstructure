package fm.pattern.commons.validation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -11353433548L;
	private final List<String> errors = new ArrayList<String>();

	public BadRequestException() {

	}

	public BadRequestException(String message) {
		super(message);
		this.errors.add(message);
	}

	public BadRequestException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
