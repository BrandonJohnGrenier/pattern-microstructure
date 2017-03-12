package fm.pattern.valex;

import java.util.List;

public class AuthenticationException extends ReportableException {

    private static final long serialVersionUID = -7022235345324655548L;

    public AuthenticationException(List<Reportable> errors) {
        super(errors);
    }

    public AuthenticationException(Reportable error) {
        super(error);
    }


}
