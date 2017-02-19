package fm.pattern.microstructure;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fm.pattern.microstructure.exceptions.ConsumableException;

public class Result<T> {

    private final T instance;
    private final ResultType type;
    private final List<Consumable> errors = new ArrayList<Consumable>();

    public static <T> Result<T> accept(T instance) {
        return new Result<T>(instance, null, new ArrayList<Consumable>());
    }

    public static <T> Result<T> created(T instance) {
        return new Result<T>(instance, ResultType.CREATED, new ArrayList<Consumable>());
    }

    public static <T> Result<T> updated(T instance) {
        return new Result<T>(instance, ResultType.UPDATED, new ArrayList<Consumable>());
    }

    public static <T> Result<T> deleted(T instance) {
        return new Result<T>(instance, ResultType.DELETED, new ArrayList<Consumable>());
    }

    public static <T> Result<T> reject(T instance, List<Consumable> errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> reject(T instance, Consumable error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> reject(T instance, String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> reject(List<Consumable> errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> reject(Consumable error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> reject(String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> unprocessable_entity(T instance, List<Consumable> errors) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> unprocessable_entity(T instance, Consumable error) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> unprocessable_entity(T instance, String description) {
        return new Result<T>(instance, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> unprocessable_entity(List<Consumable> errors) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, errors);
    }

    public static <T> Result<T> unprocessable_entity(Consumable error) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(error));
    }

    public static <T> Result<T> unprocessable_entity(String description) {
        return new Result<T>(null, ResultType.UNPROCESSABLE_ENTITY, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_found(Consumable error) {
        return new Result<T>(null, ResultType.NOT_FOUND, Arrays.asList(error));
    }

    public static <T> Result<T> not_found(String description) {
        return new Result<T>(null, ResultType.NOT_FOUND, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authorized(T instance, Consumable error) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authorized(T instance, String description) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authorized(Consumable error) {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authorized(String description) {
        return new Result<T>(null, ResultType.NOT_AUTHORIZED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authenticated(T instance, Consumable error) {
        return new Result<T>(instance, ResultType.NOT_AUTHENTICATED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authenticated(T instance, String description) {
        return new Result<T>(instance, ResultType.NOT_AUTHORIZED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> not_authenticated(Consumable error) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED, Arrays.asList(error));
    }

    public static <T> Result<T> not_authenticated(String description) {
        return new Result<T>(null, ResultType.NOT_AUTHENTICATED, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> conflict(T instance, Consumable error) {
        return new Result<T>(instance, ResultType.CONFLICT, Arrays.asList(error));
    }

    public static <T> Result<T> conflict(T instance, String description) {
        return new Result<T>(instance, ResultType.CONFLICT, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> conflict(Consumable error) {
        return new Result<T>(null, ResultType.CONFLICT, Arrays.asList(error));
    }

    public static <T> Result<T> conflict(String description) {
        return new Result<T>(null, ResultType.CONFLICT, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> bad_request(T instance, Consumable error) {
        return new Result<T>(instance, ResultType.BAD_REQUEST, Arrays.asList(error));
    }

    public static <T> Result<T> bad_request(T instance, String description) {
        return new Result<T>(instance, ResultType.BAD_REQUEST, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> bad_request(Consumable error) {
        return new Result<T>(null, ResultType.BAD_REQUEST, Arrays.asList(error));
    }

    public static <T> Result<T> bad_request(String description) {
        return new Result<T>(null, ResultType.BAD_REQUEST, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> internal_error(T instance, Consumable error) {
        return new Result<T>(instance, ResultType.INTERNAL_ERROR, Arrays.asList(error));
    }

    public static <T> Result<T> internal_error(T instance, String description) {
        return new Result<T>(instance, ResultType.INTERNAL_ERROR, Arrays.asList(convert(description)));
    }

    public static <T> Result<T> internal_error(Consumable error) {
        return new Result<T>(null, ResultType.INTERNAL_ERROR, Arrays.asList(error));
    }

    public static <T> Result<T> internal_error(String description) {
        return new Result<T>(null, ResultType.INTERNAL_ERROR, Arrays.asList(convert(description)));
    }

    public ConsumableException raise() {
        try {
            Constructor<?> constructor = type.getException().getDeclaredConstructor(List.class);
            constructor.setAccessible(true);
            return (ConsumableException) constructor.newInstance(errors);
        }
        catch (Exception e) {
            return null;
        }
    }

    private Result(T instance, ResultType type, List<Consumable> errors) {
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

    public List<Consumable> getErrors() {
        return new ArrayList<Consumable>(this.errors);
    }

    private static Consumable convert(String description) {
        return isCode(description) ? new Consumable(stripBraces(description), resolve(description), null) : new Consumable(null, resolve(description), null);
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

}
