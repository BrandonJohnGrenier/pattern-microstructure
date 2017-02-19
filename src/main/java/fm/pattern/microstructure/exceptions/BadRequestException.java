package fm.pattern.microstructure.exceptions;

import java.util.List;

import fm.pattern.microstructure.Consumable;

public class BadRequestException extends ConsumableException {

    private static final long serialVersionUID = -11353433548L;

    public BadRequestException() {

    }

    public BadRequestException(List<Consumable> errors) {
        super(errors);
    }

    public BadRequestException(String message, List<Consumable> errors) {
        super(message, errors);
    }

}
