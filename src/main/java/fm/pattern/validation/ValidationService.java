package fm.pattern.validation;

@FunctionalInterface
public interface ValidationService {

    <T> Result<T> validate(T instance, Class<?>... type);

}
