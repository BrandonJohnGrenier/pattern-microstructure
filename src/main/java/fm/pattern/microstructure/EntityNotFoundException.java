package fm.pattern.microstructure;

import java.util.List;

public class EntityNotFoundException extends ReportableException {

    private static final long serialVersionUID = -7099875229824655548L;

    public EntityNotFoundException() {

    }

    public EntityNotFoundException(List<Reportable> errors) {
        super(errors);
    }

    public EntityNotFoundException(String message, List<Reportable> errors) {
        super(message, errors);
    }

    public EntityNotFoundException(Reportable error) {
        super(error);
    }

    public EntityNotFoundException(String message, Reportable error) {
        super(message, error);
    }
    
}
