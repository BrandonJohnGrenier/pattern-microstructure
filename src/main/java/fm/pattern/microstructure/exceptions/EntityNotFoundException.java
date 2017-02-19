package fm.pattern.microstructure.exceptions;

import java.util.ArrayList;
import java.util.List;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7099875229824655548L;
	private final List<String> errors = new ArrayList<String>();

	public EntityNotFoundException() {

	}

	public EntityNotFoundException(String message) {
		super(message);
		this.errors.add(message);
	}

	public EntityNotFoundException(List<String> errors) {
		super(errors == null ? "" : errors.toString());
		this.errors.addAll(errors);
	}

	public List<String> getErrors() {
		return errors;
	}

}
