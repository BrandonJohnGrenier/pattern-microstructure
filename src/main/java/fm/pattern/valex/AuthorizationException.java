package fm.pattern.valex;

import java.util.List;

public class AuthorizationException extends ReportableException {

    private static final long serialVersionUID = -7022235229824655548L;

    public AuthorizationException(List<Reportable> errors) {
        super(errors);
    }

    public AuthorizationException(Reportable error) {
        super(error);
    }
    
}
