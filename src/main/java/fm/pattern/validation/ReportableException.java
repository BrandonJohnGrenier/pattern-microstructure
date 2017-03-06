package fm.pattern.validation;

import java.util.ArrayList;
import java.util.List;

public class ReportableException extends RuntimeException {

    private static final long serialVersionUID = -7677235345324655548L;
    private final transient List<Reportable> errors = new ArrayList<>();

    public ReportableException(List<Reportable> errors) {
        this.errors.addAll(errors);
    }

    public ReportableException(Reportable error) {
        this.errors.add(error);
    }

    public List<Reportable> getErrors() {
        return errors;
    }

}
