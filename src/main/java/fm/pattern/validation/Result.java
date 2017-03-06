package fm.pattern.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fm.pattern.commons.util.JSON;

public class Result<T> {

    private final T instance;
    private final List<Reportable> errors = new ArrayList<>();

    private Result(T instance) {
        this.instance = instance;
    }

    private Result(T instance, Reportable... errors) {
        this.instance = instance;
        this.errors.addAll(Arrays.asList(errors));
    }

    public static <T> Result<T> accept(T instance) {
        return new Result<>(instance);
    }

    public static <T> Result<T> reject(String key, Object... arguments) {
        return new Result<>(null, Reportable.report(key, arguments));
    }

    public static <T> Result<T> reject(Reportable... errors) {
        return new Result<>(null, errors);
    }

    public T getInstance() {
        return instance;
    }

    public T orThrow() {
        if (rejected()) {
            throw ReportableExceptionRaiser.raise(errors);
        }
        return instance;
    }

    public T orThrow(Class<? extends ReportableException> exception) {
        if (rejected()) {
            throw ReportableExceptionRaiser.raise(exception, errors);
        }
        return instance;
    }

    public boolean accepted() {
        return errors.isEmpty();
    }

    public boolean rejected() {
        return !errors.isEmpty();
    }

    public List<Reportable> getErrors() {
        return new ArrayList<>(this.errors);
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }

}
