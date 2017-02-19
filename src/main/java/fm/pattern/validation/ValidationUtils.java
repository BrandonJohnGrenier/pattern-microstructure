package fm.pattern.validation;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

public final class ValidationUtils {

	private ValidationUtils() {

	}

	public static <T> List<String> extract(Set<ConstraintViolation<T>> violations) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<T> violation : violations) {
			if (isNotEmpty(violation.getMessage())) {
				errors.add(violation.getMessage());
			}
		}
		return errors;
	}

	public static <T> ValidationResult validate(Set<ConstraintViolation<T>> violations) {
		List<String> messages = new ArrayList<String>();
		ValidationResult errors = new ValidationResult();

		for (ConstraintViolation<T> violation : violations) {
			if (isNotEmpty(violation.getMessage())) {
				messages.add(violation.getMessage());
			}
		}

		return errors.withMessages(messages);
	}

}
