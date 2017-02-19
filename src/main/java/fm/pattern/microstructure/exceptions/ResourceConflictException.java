package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Consumable;

public class ResourceConflictException extends ConsumableException {

    private static final long serialVersionUID = -7735345324657785548L;

    public ResourceConflictException() {

    }

    public ResourceConflictException(List<Consumable> errors) {
        super(errors);
    }

    public ResourceConflictException(String message, List<Consumable> errors) {
        super(message, errors);
    }

}
