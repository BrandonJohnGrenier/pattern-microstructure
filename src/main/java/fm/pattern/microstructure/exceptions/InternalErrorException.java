package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Consumable;

public class InternalErrorException extends ConsumableException {

    private static final long serialVersionUID = -7093595345324626648L;

    public InternalErrorException() {

    }

    public InternalErrorException(List<Consumable> errors) {
        super(errors);
    }

    public InternalErrorException(String message, List<Consumable> errors) {
        super(message, errors);
    }

}
