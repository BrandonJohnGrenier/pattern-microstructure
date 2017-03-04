package fm.pattern.microstructure;

import java.util.List;

public class InternalErrorException extends ReportableException {

    private static final long serialVersionUID = -7093595345324626648L;

    public InternalErrorException() {

    }

    public InternalErrorException(List<Reportable> errors) {
        super(errors);
    }

    public InternalErrorException(String message, List<Reportable> errors) {
        super(message, errors);
    }

    public InternalErrorException(Reportable error) {
        super(error);
    }

    public InternalErrorException(String message, Reportable error) {
        super(message, error);
    }
    
}
