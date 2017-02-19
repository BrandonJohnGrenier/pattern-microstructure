package fm.pattern.microstructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationResult {

    private final List<Error> errors = new ArrayList<Error>();

    public ValidationResult() {

    }

    public ValidationResult(List<Error> errors) {
        withErrors(errors);
    }

    public ValidationResult withError(Error error) {
        return withErrors(Arrays.asList(error));
    }

    public ValidationResult withErrors(List<Error> errors) {
        this.errors.addAll(errors.stream().filter(error -> error != null).collect(Collectors.toList()));
        return this;
    }

    public List<Error> getErrors() {
        return new ArrayList<Error>(errors);
    }

    public <T> Result<T> reject(T instance) {
        return Result.reject(instance, getErrors());
    }

    public <T> Result<T> accept(T instance) {
        return Result.accept(instance);
    }

    public boolean containsErrors() {
        return !errors.isEmpty();
    }

}
