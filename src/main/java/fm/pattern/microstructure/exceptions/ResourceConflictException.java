package fm.pattern.microstructure.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ResourceConflictException extends RuntimeException {

	private static final long serialVersionUID = -7735345324657785548L;
	private final List<String> errors = new ArrayList<String>();

	public ResourceConflictException() {

	}

	public ResourceConflictException(String message) {
		super(message);
		this.errors.add(message);
	}

	public ResourceConflictException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
