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

    public static <T> Result<T> reject(T instance) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY);
    }

    public static <T> Result<T> reject() {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY);
    }

    public static <T> Result<T> invalid(T instance) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY);
    }

    public static <T> Result<T> invalid() {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY);
    }

    public static <T> Result<T> not_found(T instance) {
        return new Result<T>(instance, ResultType.NOT_FOUND);
    }

    public static <T> Result<T> not_found() {
        return new Result<T>(null, ResultType.NOT_FOUND);
    }

    public static <T> Result<T> unauthorized(T instance) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED);
    }

    public static <T> Result<T> unauthorized() {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED);
    }

    public static <T> Result<T> not_authenticated(T instance) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED);
    }

    public static <T> Result<T> not_authenticated(T instance, String key, Object... arguments) {
        return new Result<T>(instance, ResultType.NOT_AUTHENTICATED, Reportable.report(key, arguments));
    }

    public static <T> Result<T> conflict(T instance) {
        return new Result<T>(instance, ResultType.CONFLICT);
    }

    public static <T> Result<T> conflict() {
        return new Result<T>(null, ResultType.CONFLICT);
    }

    public static <T> Result<T> bad_request(T instance) {
        return new Result<T>(null, ResultType.BAD_REQUEST);
    }

    public static <T> Result<T> bad_request() {
        return new Result<T>(null, ResultType.BAD_REQUEST);
    }

    public static <T> Result<T> internal_error(T instance) {
        return new Result<T>(instance, ResultType.INTERNAL_ERROR);
    }

    public static <T> Result<T> internal_error() {
        return new Result<T>(null, ResultType.INTERNAL_ERROR);
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

    public Result<T> with(Reportable... errors) {
        this.errors.addAll(Arrays.asList(errors));
        return this;
    }

    public Result<T> with(String key, Object... arguments) {
        this.errors.add(Reportable.report(key, arguments));
        return this;
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
