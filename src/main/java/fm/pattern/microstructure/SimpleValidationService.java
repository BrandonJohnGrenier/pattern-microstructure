package fm.pattern.microstructure;

import java.util.Arrays;
import java.util.List;

import javax.validation.Validator;

import fm.pattern.microstructure.sequences.Create;
import fm.pattern.microstructure.sequences.Delete;
import fm.pattern.microstructure.sequences.Update;

public class SimpleValidationService implements ValidationService {

    private final Validator validator;

    public SimpleValidationService(Validator validator) {
        this.validator = validator;
    }

    public <T> Result<T> validate(T instance, Class<?>... types) {
        if(instance == null) {
            return Result.bad_request("instance.null");
        }
        
        List<Reportable> errors = ConstraintViolationConverter.convert(validator.validate(instance, types));
        return !errors.isEmpty() ? Result.invalid(errors.toArray(new Reportable[errors.size()])) : accept(instance, types);
    }

    private <T> Result<T> accept(T instance, Class<?>... types) {
        List<Class<?>> list = Arrays.asList(types);

        if (list.contains(Create.class)) {
            return Result.created(instance);
        }
        if (list.contains(Update.class)) {
            return Result.updated(instance);
        }
        if (list.contains(Delete.class)) {
            return Result.deleted(instance);
        }

        return Result.accept(instance);
    }

}
