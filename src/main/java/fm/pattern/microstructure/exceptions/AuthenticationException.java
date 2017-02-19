package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Consumable;

public class AuthenticationException extends ConsumableException {

    private static final long serialVersionUID = -7022235345324655548L;

    public AuthenticationException() {

    }

    public AuthenticationException(List<Consumable> errors) {
        super(errors);
    }

    public AuthenticationException(String message, List<Consumable> errors) {
        super(message, errors);
    }

}
