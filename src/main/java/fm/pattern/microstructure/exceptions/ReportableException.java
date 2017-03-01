package fm.pattern.microstructure.exceptions;

import java.util.ArrayList;
import java.util.List;

import fm.pattern.microstructure.Reportable;

public class ReportableException extends RuntimeException {

    private static final long serialVersionUID = -7677235345324655548L;
    private final List<Reportable> errors = new ArrayList<Reportable>();

    public ReportableException() {

    }

    public ReportableException(String message, List<Reportable> errors) {
        super(message);
        this.errors.addAll(errors);
    }

    public ReportableException(List<Reportable> errors) {
        this.errors.addAll(errors);
    }

    public ReportableException(String message, Reportable error) {
        super(message);
        this.errors.add(error);
    }

    public ReportableException(Reportable error) {
        this.errors.add(error);
    }

    public List<Reportable> getErrors() {
        return errors;
    }

}
