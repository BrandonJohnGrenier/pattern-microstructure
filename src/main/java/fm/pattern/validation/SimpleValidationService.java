package fm.pattern.validation;

import javax.validation.Validator;

public class SimpleValidationService implements ValidationService {

    private final Validator validator;

    public SimpleValidationService(Validator validator) {
        this.validator = validator;
    }

    public <T> Result<T> validate(T instance, Class<?>... types) {
        ValidationResult result = ErrorConverter.convert(validator.validate(instance, types));
        return result.containsErrors() ? Result.reject(instance, result.getErrors()) : Result.accept(instance);
    }

}
