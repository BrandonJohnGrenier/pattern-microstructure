package fm.pattern.microstructure;

public class Consumable {

    private final String code;
    private final String description;
    private final String property;

    public Consumable(String code, String description, String property) {
        this.code = code;
        this.description = description;
        this.property = property;
    }

    public Consumable(String code, String description) {
        this.code = code;
        this.description = description;
        this.property = null;
    }
    
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getProperty() {
        return property;
    }

}
