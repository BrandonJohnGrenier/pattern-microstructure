package fm.pattern.validation;

public interface ValidationService {

    <T> Result<T> validate(T instance, Class<?>... type);

}
