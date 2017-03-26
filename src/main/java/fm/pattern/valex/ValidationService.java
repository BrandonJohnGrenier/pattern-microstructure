package fm.pattern.valex;

@FunctionalInterface
public interface ValidationService {

    <T> Result<T> validate(T instance, Class<?>... groups);

}
