package fm.pattern.valex;

import java.util.List;

public class UnprocessableEntityException extends ReportableException {

    private static final long serialVersionUID = -7333875229824655548L;

    public UnprocessableEntityException(List<Reportable> errors) {
        super(errors);
    }

    public UnprocessableEntityException(Reportable error) {
        super(error);
    }

}
