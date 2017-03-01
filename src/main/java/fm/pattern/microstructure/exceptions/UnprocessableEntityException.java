package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Reportable;

public class UnprocessableEntityException extends ReportableException {

    private static final long serialVersionUID = -7333875229824655548L;

    public UnprocessableEntityException() {

    }

    public UnprocessableEntityException(List<Reportable> errors) {
        super(errors);
    }

    public UnprocessableEntityException(String message, List<Reportable> errors) {
        super(message, errors);
    }

    public UnprocessableEntityException(Reportable error) {
        super(error);
    }

    public UnprocessableEntityException(String message, Reportable error) {
        super(message, error);
    }
    
}
