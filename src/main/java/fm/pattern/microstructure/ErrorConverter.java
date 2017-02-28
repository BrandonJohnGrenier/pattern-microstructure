package fm.pattern.microstructure;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

public final class ErrorConverter {

    private ErrorConverter() {

    }

    public static <T> List<Reportable> convert(Set<ConstraintViolation<T>> violations) {
        List<Reportable> errors = new ArrayList<Reportable>();
        if (violations == null || violations.isEmpty()) {
            return errors;
        }

        for (ConstraintViolation<T> violation : violations) {
            if (isNotEmpty(violation.getMessage())) {
                String code = violation.getMessageTemplate().replace("{", "").replace("}", "");
                String description = violation.getMessage();
                String property = violation.getPropertyPath().toString();
                errors.add(new Reportable(code, description, property));
            }
        }

        return errors;
    }

}
