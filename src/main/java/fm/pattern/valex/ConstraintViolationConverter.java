package fm.pattern.valex;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;

import fm.pattern.valex.config.ValexConfigurationFactory;

public final class ConstraintViolationConverter {

    private ConstraintViolationConverter() {

    }

    public static <T> List<Reportable> convert(Set<ConstraintViolation<T>> violations) {
        List<Reportable> errors = new ArrayList<>();
        if (violations == null || violations.isEmpty()) {
            return errors;
        }

        for (ConstraintViolation<T> violation : violations) {
            if (isNotEmpty(violation.getMessage())) {
                String key = violation.getMessageTemplate().replace("{", "").replace("}", "");
                String message = ValexConfigurationFactory.getRepository().getMessage(key);
                errors.add(Reportable.interpolated(key, interpolate(message, violation)));
            }
        }

        return errors;
    }

    private static <T> String interpolate(String message, ConstraintViolation<T> violation) {
        Map<String, Object> attributes = annotationAttributes(violation);

        String interpolated = message;
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            interpolated = interpolated.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }

        if (violation.getInvalidValue() != null) {
            interpolated = interpolated.replace("{validatedValue}", violation.getInvalidValue().toString());
        }

        return interpolated;
    }

    private static <T> Map<String, Object> annotationAttributes(ConstraintViolation<T> violation) {
        ConstraintDescriptor<?> constraint = violation.getConstraintDescriptor();

        Map<String, Object> attributes = new HashMap<>(constraint.getAttributes());
        attributes.remove("groups");
        attributes.remove("payload");
        attributes.remove("message");
        return attributes;
    }

}
