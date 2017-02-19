package fm.pattern.validation;

public interface ValidationService {

    <T> ValidationResult validate(T instance, Class<?>... type);

}
