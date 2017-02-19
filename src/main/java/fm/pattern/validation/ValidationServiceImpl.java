package fm.pattern.validation;

import javax.validation.Validator;

class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    ValidationServiceImpl(Validator validator) {
        this.validator = validator;
    }

    public <T> ValidationResult validate(T instance, Class<?>... types) {
        return ValidationUtils.validate(validator.validate(instance, types));
    }

}
