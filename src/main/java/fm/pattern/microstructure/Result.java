package fm.pattern.microstructure;

import static org.apache.commons.lang3.StringUtils.isBlank;

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
        return new Result<T>(instance, null, new ArrayList<Reportable>());
    }

    public static <T> Result<T> created(T instance) {
        return new Result<T>(instance, ResultType.CREATED, new ArrayList<Reportable>());
    }

    public static <T> Result<T> updated(T instance) {
        return new Result<T>(instance, ResultType.UPDATED, new ArrayList<Reportable>());
    }

    public static <T> Result<T> deleted(T instance) {
        return new Result<T>(instance, ResultType.DELETED, new ArrayList<Reportable>());
    }

    public static <T> Result<T> reject(T instance, List<Reportable> errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> reject(T instance, Reportable error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> reject(T instance, String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> reject(List<Reportable> errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> reject(Reportable error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> reject(String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> unprocessable_entity(T instance, List<Reportable> errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> unprocessable_entity(T instance, Reportable error) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> unprocessable_entity(T instance, String description) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> unprocessable_entity(List<Reportable> errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> unprocessable_entity(Reportable error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> unprocessable_entity(String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_found(Reportable error) {
        return new Result<T>(null, ResultType.NOT_FOUND, Arrays.asList(error));
    }

    public static <T> Result<T> not_found(String description) {
        return new Result<T>(null, ResultType.NOT_FOUND, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authorized(T instance, Reportable error) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authorized(T instance, String description) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authorized(Reportable error) {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authorized(String description) {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authenticated(T instance, Reportable error) {
        return new Result<T>(instance, ResultType.NOT_AUTHENTICATED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authenticated(T instance, String description) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authenticated(Reportable error) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authenticated(String description) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> conflict(T instance, Reportable error) {
        return new Result<T>(instance, ResultType.CONFLICT, Arrays.asList(error));
    }

    public static <T> Result<T> conflict(T instance, String description) {
        return new Result<T>(instance, ResultType.CONFLICT, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> conflict(Reportable error) {
        return new Result<T>(null, ResultType.CONFLICT, Arrays.asList(error));
    }

    public static <T> Result<T> conflict(String description) {
        return new Result<T>(null, ResultType.CONFLICT, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> bad_request(T instance, Reportable error) {
        return new Result<T>(instance, ResultType.BAD_REQUEST, Arrays.asList(error));
    }

    public static <T> Result<T> bad_request(T instance, String description) {
        return new Result<T>(instance, ResultType.BAD_REQUEST, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> bad_request(Reportable error) {
        return new Result<T>(null, ResultType.BAD_REQUEST, Arrays.asList(error));
    }

    public static <T> Result<T> bad_request(String description) {
        return new Result<T>(null, ResultType.BAD_REQUEST, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> internal_error(T instance, Reportable error) {
        return new Result<T>(instance, ResultType.INTERNAL_ERROR, Arrays.asList(error));
    }

    public static <T> Result<T> internal_error(T instance, String description) {
        return new Result<T>(instance, ResultType.INTERNAL_ERROR, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> internal_error(Reportable error) {
        return new Result<T>(null, ResultType.INTERNAL_ERROR, Arrays.asList(error));
    }

    public static <T> Result<T> internal_error(String description) {
        return new Result<T>(null, ResultType.INTERNAL_ERROR, Arrays.asList(convert(description)));
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

    private Result(T instance, ResultType type, List<Reportable> errors) {
        this.instance = instance;
        this.type = type;
        this.errors.addAll(errors);
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

    private static Reportable convert(String description) {
        return isCode(description) ? new Reportable(stripBraces(description), resolve(description), null) : new Reportable(null, resolve(description), null);
    }

    private static String resolve(String description) {
        if (isBlank(description)) {
            return description;
        }
        if (isCode(description)) {
            return ValidationMessages.getMessage(stripBraces(description));
        }
        return description;
    }

    private static String stripBraces(String text) {
        return text.replace("{", "").replace("}", "");
    }

    private static boolean isCode(String text) {
        return (text.startsWith("{") && text.endsWith("}"));
    }

    public String toString() {
        return JSON.stringify(this);
    }

}
