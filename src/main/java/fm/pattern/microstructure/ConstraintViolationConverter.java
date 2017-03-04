package fm.pattern.microstructure;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;

public final class ConstraintViolationConverter {

    private ConstraintViolationConverter() {

    }

    public static <T> List<Reportable> convert(Set<ConstraintViolation<T>> violations) {
        List<Reportable> errors = new ArrayList<Reportable>();
        if (violations == null || violations.isEmpty()) {
            return errors;
        }

        for (ConstraintViolation<T> violation : violations) {
            if (isNotEmpty(violation.getMessage())) {
                String key = violation.getMessageTemplate().replace("{", "").replace("}", "");
                errors.add(Reportable.report_interpolated(key, interpolate(violation.getMessage(), violation)));
            }
        }

        return errors;
    }

    private static <T> String interpolate(String message, ConstraintViolation<T> violation) {
        Map<String, Object> attributes = annotationAttributes(violation);

        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }

        if (violation.getInvalidValue() != null) {
            message = message.replace("{validatedValue}", violation.getInvalidValue().toString());
        }

        return message;
    }

    private static <T> Map<String, Object> annotationAttributes(ConstraintViolation<T> violation) {
        ConstraintDescriptor<?> constraint = violation.getConstraintDescriptor();

        Map<String, Object> attributes = new HashMap<String, Object>(constraint.getAttributes());
        attributes.remove("groups");
        attributes.remove("payload");
        attributes.remove("message");
        return attributes;
    }

}
