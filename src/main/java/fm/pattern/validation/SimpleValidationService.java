package fm.pattern.validation;

import javax.validation.Validator;

public class SimpleValidationService implements ValidationService {

    private final Validator validator;

    public SimpleValidationService(Validator validator) {
        this.validator = validator;
    }

    public <T> ValidationResult validate(T instance, Class<?>... types) {
        return ValidationUtils.validate(validator.validate(instance, types));
    }

}
