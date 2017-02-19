package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Consumable;

public class UnprocessableEntityException extends ConsumableException {

    private static final long serialVersionUID = -7333875229824655548L;

    public UnprocessableEntityException() {

    }

    public UnprocessableEntityException(List<Consumable> errors) {
        super(errors);
    }

    public UnprocessableEntityException(String message, List<Consumable> errors) {
        super(message, errors);
    }

}
