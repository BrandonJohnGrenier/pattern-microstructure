package fm.pattern.validation;

import java.util.List;

public class InternalErrorException extends ReportableException {

    private static final long serialVersionUID = -7093595345324626648L;

    public InternalErrorException(List<Reportable> errors) {
        super(errors);
    }

    public InternalErrorException(Reportable error) {
        super(error);
    }

}
