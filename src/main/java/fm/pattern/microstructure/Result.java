package fm.pattern.microstructure;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result<T> {

    private final T instance;
    private final ResultType type;
    private final List<Error> errors = new ArrayList<Error>();

    public static <T> Result<T> accept(T instance) {
        return new Result<T>(instance, null, new ArrayList<Error>());
    }

    public static <T> Result<T> created(T instance) {
        return new Result<T>(instance, ResultType.CREATED, new ArrayList<Error>());
    }

    public static <T> Result<T> updated(T instance) {
        return new Result<T>(instance, ResultType.UPDATED, new ArrayList<Error>());
    }

    public static <T> Result<T> deleted(T instance) {
        return new Result<T>(instance, ResultType.DELETED, new ArrayList<Error>());
    }

    public static <T> Result<T> reject(T instance, List<Error> errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> reject(T instance, Error error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> reject(T instance, String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> reject(List<Error> errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> reject(Error error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> reject(String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> unprocessable_entity(T instance, List<Error> errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> unprocessable_entity(T instance, Error error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> unprocessable_entity(T instance, String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> unprocessable_entity(List<Error> errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> unprocessable_entity(Error error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> unprocessable_entity(String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_found(Error error) {
        return new Result<T>(null, ResultType.NOT_FOUND, Arrays.asList(error));
    }

    public static <T> Result<T> not_found(String description) {
        return new Result<T>(null, ResultType.NOT_FOUND, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authorized(Error error) {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authorized(String description) {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authenticated(Error error) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authenticated(String description) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> conflict(Error error) {
        return new Result<T>(null, ResultType.CONFLICT, Arrays.asList(error));
    }

    public static <T> Result<T> conflict(String description) {
        return new Result<T>(null, ResultType.CONFLICT, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> bad_request(Error error) {
        return new Result<T>(null, ResultType.BAD_REQUEST, Arrays.asList(error));
    }

    public static <T> Result<T> bad_request(String description) {
        return new Result<T>(null, ResultType.BAD_REQUEST, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> internal_error(Error error) {
        return new Result<T>(null, ResultType.INTERNAL_ERROR, Arrays.asList(error));
    }

    public static <T> Result<T> internal_error(String description) {
        return new Result<T>(null, ResultType.INTERNAL_ERROR, Arrays.asList(convert(description)));
    }
    
    private Result(T instance, ResultType type, List<Error> errors) {
        this.instance = instance;
        this.type = type;
        this.errors.addAll(errors);
    }

    public T getInstance() {
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

    public List<Error> getErrors() {
        return new ArrayList<Error>(this.errors);
    }

    private static Error convert(String description) {
        return new Error(null, resolve(description), null);
    }

    private static String resolve(String description) {
        if (isBlank(description)) {
            return description;
        }
        if (description.startsWith("{") && description.endsWith("}")) {
            return ValidationMessages.getMessage(description.replace("{", "").replace("}", ""));
        }
        return description;
    }

}
