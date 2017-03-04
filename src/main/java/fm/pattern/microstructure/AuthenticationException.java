package fm.pattern.microstructure;

import java.util.List;

public class AuthenticationException extends ReportableException {

    private static final long serialVersionUID = -7022235345324655548L;

    public AuthenticationException() {

    }

    public AuthenticationException(List<Reportable> errors) {
        super(errors);
    }

    public AuthenticationException(String message, List<Reportable> errors) {
        super(message, errors);
    }

    public AuthenticationException(Reportable error) {
        super(error);
    }

    public AuthenticationException(String message, Reportable error) {
        super(message, error);
    }

}
