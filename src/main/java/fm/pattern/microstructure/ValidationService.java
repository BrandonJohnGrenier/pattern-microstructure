package fm.pattern.microstructure;

public interface ValidationService {

    <T> Result<T> validate(T instance, Class<?>... type);

}
