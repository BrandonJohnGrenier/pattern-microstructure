package fm.pattern.validation;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.Set;

import javax.validation.ConstraintViolation;

public final class ErrorConverter {

    private ErrorConverter() {

    }

    public static <T> ValidationResult convert(Set<ConstraintViolation<T>> violations) {
        ValidationResult result = new ValidationResult();

        for (ConstraintViolation<T> violation : violations) {
            if (isNotEmpty(violation.getMessage())) {
                String code = violation.getMessageTemplate().replace("{", "").replace("}", "");
                String description = violation.getMessage();
                String property = violation.getPropertyPath().toString();
                result.withError(new Error(code, description, property));
            }
        }

        return result;
    }

}
