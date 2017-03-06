package fm.pattern.validation;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class ReportableExceptionRaiser {

    private static final Log log = LogFactory.getLog(ReportableExceptionRaiser.class);
    private static final Class<? extends ReportableException> DEFAULT_EXCEPTION = UnprocessableEntityException.class;

    public static ReportableException raise(Class<? extends ReportableException> exception, List<Reportable> errors) {
        Class<? extends ReportableException> actual = exception == null ? DEFAULT_EXCEPTION : exception;

        try {
            Constructor<?> constructor = actual.getDeclaredConstructor(List.class);
            constructor.setAccessible(true);
            return (ReportableException) constructor.newInstance(filter(errors));
        }
        catch (Exception e) {
            log.error("Failed to instantiate a ReportableException class:", e);
            return new UnprocessableEntityException(errors);
        }
    }

    public static ReportableException raise(List<Reportable> errors) {
        Class<? extends ReportableException> exception = filter(errors).stream().map(e -> e.getException()).filter(e -> e != null).findFirst().orElse(null);
        return ReportableExceptionRaiser.raise((exception == null ? DEFAULT_EXCEPTION : exception), errors);
    }

    private static List<Reportable> filter(List<Reportable> errors) {
        return errors.stream().filter(error -> error != null).collect(Collectors.toList());
    }

}
