package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Consumable;

public class AuthorizationException extends ConsumableException {

    private static final long serialVersionUID = -7022235229824655548L;

    public AuthorizationException() {

    }

    public AuthorizationException(List<Consumable> errors) {
        super(errors);
    }

    public AuthorizationException(String message, List<Consumable> errors) {
        super(message, errors);
    }

}
