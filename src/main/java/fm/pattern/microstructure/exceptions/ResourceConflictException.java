package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Reportable;

public class ResourceConflictException extends ReportableException {

    private static final long serialVersionUID = -7735345324657785548L;

    public ResourceConflictException() {

    }

    public ResourceConflictException(List<Reportable> errors) {
        super(errors);
    }

    public ResourceConflictException(String message, List<Reportable> errors) {
        super(message, errors);
    }

    public ResourceConflictException(Reportable error) {
        super(error);
    }

    public ResourceConflictException(String message, Reportable error) {
        super(message, error);
    }
    
}
