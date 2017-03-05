package fm.pattern.validation;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fm.pattern.commons.util.JSON;

public class Result<T> {

    private static final Log log = LogFactory.getLog(Result.class);
    private static final Class<? extends ReportableException> DEFAULT_EXCEPTION = UnprocessableEntityException.class;

    private final T instance;
    private final List<Reportable> errors = new ArrayList<>();

    public static <T> Result<T> accept(T instance) {
        return new Result<T>(instance);
    }

    public static <T> Result<T> reject(String key, Object... arguments) {
        return new Result<T>(null, Reportable.report(key, arguments));
    }

    public static <T> Result<T> reject(Reportable... errors) {
        return new Result<>(null, errors);
    }

    private Result(T instance) {
        this.instance = instance;
    }

    private Result(T instance, Reportable... errors) {
        this.instance = instance;
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

    public List<Reportable> getErrors() {
        return new ArrayList<>(this.errors);
    }

    public String toString() {
        return JSON.stringify(this);
    }

    private ReportableException raise(Class<? extends ReportableException> exception) {
        if (exception == null) {
            return null;
        }

        try {
            Constructor<?> constructor = exception.getDeclaredConstructor(List.class);
            constructor.setAccessible(true);
            return (ReportableException) constructor.newInstance(errors);
        }
        catch (Exception e) {
            log.error("Failed to instantiate a ReportableException class:", e);
            return null;
        }
    }

    private ReportableException raise() {
        Class<? extends ReportableException> l = errors.stream().map(e -> e.getException()).filter(e -> e != null).findFirst().orElse(null);
        return raise(l == null ? DEFAULT_EXCEPTION : l);
    }

}
