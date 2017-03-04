package fm.pattern.microstructure;

import java.util.List;

import javax.validation.Validator;

public class SimpleValidationService implements ValidationService {

    private final Validator validator;

    public SimpleValidationService(Validator validator) {
        this.validator = validator;
    }

    public <T> Result<T> validate(T instance, Class<?>... types) {
        if (instance == null) {
            return Result.reject("instance.null");
        }

        List<Reportable> errors = ConstraintViolationConverter.convert(validator.validate(instance, types));
        return !errors.isEmpty() ? Result.reject(errors.toArray(new Reportable[errors.size()])) : accept(instance);
    }

    private <T> Result<T> accept(T instance) {
        return Result.accept(instance);
    }

}
