package fm.pattern.microstructure;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

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
                
                
                errors.add(Reportable.report_bv(key, violation.getMessage()));
            }
        }

        return errors;
    }

}
