package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Reportable;

public class BadRequestException extends ReportableException {

    private static final long serialVersionUID = -11353433548L;

    public BadRequestException() {

    }

    public BadRequestException(List<Reportable> errors) {
        super(errors);
    }

    public BadRequestException(String message, List<Reportable> errors) {
        super(message, errors);
    }

    public BadRequestException(Reportable error) {
        super(error);
    }

    public BadRequestException(String message, Reportable error) {
        super(message, error);
    }
    
}
