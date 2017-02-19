package fm.pattern.microstructure;

public class Error {

    private final String code;
    private final String description;
    private final String property;

    public Error(String code, String description, String property) {
        this.code = code;
        this.description = description;
        this.property = property;
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
