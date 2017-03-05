package fm.pattern.validation;

import java.util.List;

public class ResourceConflictException extends ReportableException {

    private static final long serialVersionUID = -7735345324657785548L;

    public ResourceConflictException(List<Reportable> errors) {
        super(errors);
    }

    public ResourceConflictException(Reportable error) {
        super(error);
    }

}
