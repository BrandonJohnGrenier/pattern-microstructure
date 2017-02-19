package fm.pattern.validation.exceptions;

import java.util.ArrayList;
import java.util.List;

public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = -7333875229824655548L;
	private final List<String> errors = new ArrayList<String>();

	public UnprocessableEntityException() {

	}

	public UnprocessableEntityException(String message) {
		super(message);
		this.errors.add(message);
	}

	public UnprocessableEntityException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
