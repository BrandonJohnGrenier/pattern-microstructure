package fm.pattern.microstructure;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fm.pattern.commons.util.JSON;
import fm.pattern.microstructure.exceptions.ReportableException;

public class Result<T> {

    private final T instance;
    private final ResultType type;
    private final List<Reportable> errors = new ArrayList<Reportable>();

    public static <T> Result<T> accept(T instance) {
        return new Result<T>(instance, ResultType.OK);
    }

    public static <T> Result<T> created(T instance) {
        return new Result<T>(instance, ResultType.CREATED);
    }

    public static <T> Result<T> updated(T instance) {
        return new Result<T>(instance, ResultType.UPDATED);
    }

    public static <T> Result<T> deleted(T instance) {
        return new Result<T>(instance, ResultType.DELETED);
    }

    public static <T> Result<T> reject(Reportable... errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> reject(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> invalid(Reportable... errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> invalid(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> not_found(Reportable... errors) {
        return new Result<T>(null, ResultType.NOT_FOUND, errors);
    }

    public static <T> Result<T> not_found(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.NOT_FOUND, errors);
    }

    public static <T> Result<T> not_authorized(Reportable... errors) {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED, errors);
    }

    public static <T> Result<T> not_authorized(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED, errors);
    }

    public static <T> Result<T> not_authenticated(Reportable... errors) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED, errors);
    }

    public static <T> Result<T> not_authenticated(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.NOT_AUTHENTICATED, errors);
    }

    public static <T> Result<T> conflict(Reportable... errors) {
        return new Result<T>(null, ResultType.CONFLICT, errors);
    }

    public static <T> Result<T> conflict(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.CONFLICT, errors);
    }

    public static <T> Result<T> bad_request(Reportable... errors) {
        return new Result<T>(null, ResultType.BAD_REQUEST, errors);
    }

    public static <T> Result<T> bad_request(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.BAD_REQUEST, errors);
    }

    public static <T> Result<T> internal_error(Reportable... errors) {
        return new Result<T>(null, ResultType.INTERNAL_ERROR, errors);
    }

    public static <T> Result<T> internal_error(T instance, Reportable... errors) {
        return new Result<T>(instance, ResultType.INTERNAL_ERROR, errors);
    }

    public ReportableException raise(Class<? extends ReportableException> exception) {
        if (exception == null) {
            return null;
        }

        try {
            Constructor<?> constructor = exception.getDeclaredConstructor(List.class);
            constructor.setAccessible(true);
            return (ReportableException) constructor.newInstance(errors);
        }
        catch (Exception e) {
            return null;
        }
    }

    public ReportableException raise() {
        return (type == null || type.getException() == null) ? null : raise(type.getException());
    }

    private Result(T instance, ResultType type) {
        this.instance = instance;
        this.type = type;
    }

    private Result(T instance, ResultType type, Reportable... errors) {
        this.instance = instance;
        this.type = type;
        this.errors.addAll(Arrays.asList(errors));
    }

    public T getInstance() {
        return instance;
    }

    public T orThrow() {
        if (rejected()) {
            throw raise();
        }
        return instance;
    }

    public T orThrow(Class<? extends ReportableException> exception) {
        if (rejected()) {
            throw raise(exception);
        }
        return instance;
    }

    public boolean accepted() {
        return errors.isEmpty();
    }

    public boolean rejected() {
        return !errors.isEmpty();
    }

    public ResultType getType() {
        return type;
    }

    public List<Reportable> getErrors() {
        return new ArrayList<Reportable>(this.errors);
    }

    public String toString() {
        return JSON.stringify(this);
    }

}
