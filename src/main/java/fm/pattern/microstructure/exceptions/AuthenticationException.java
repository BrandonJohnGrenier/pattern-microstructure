package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Reportable;

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

}
