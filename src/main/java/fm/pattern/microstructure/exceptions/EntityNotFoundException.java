package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Consumable;

public class EntityNotFoundException extends ConsumableException {

    private static final long serialVersionUID = -7099875229824655548L;

    public EntityNotFoundException() {

    }

    public EntityNotFoundException(List<Consumable> errors) {
        super(errors);
    }

    public EntityNotFoundException(String message, List<Consumable> errors) {
        super(message, errors);
    }

}
