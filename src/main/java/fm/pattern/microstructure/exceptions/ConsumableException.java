package fm.pattern.microstructure.exceptions;

import java.util.ArrayList;
import java.util.List;

import fm.pattern.microstructure.Consumable;

public class ConsumableException extends RuntimeException {

    private static final long serialVersionUID = -7677235345324655548L;
    private final List<Consumable> errors = new ArrayList<Consumable>();

    public ConsumableException() {

    }

    public ConsumableException(String message, List<Consumable> errors) {
        super(message);
        this.errors.addAll(errors);
    }

    public ConsumableException(List<Consumable> errors) {
        this.errors.addAll(errors);
    }

    public List<Consumable> getErrors() {
        return errors;
    }

}
