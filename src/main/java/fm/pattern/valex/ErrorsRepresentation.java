package fm.pattern.valex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorsRepresentation {

    private List<Map<String, String>> errors = new ArrayList<Map<String, String>>();

    public ErrorsRepresentation() {

    }

    public ErrorsRepresentation(List<Map<String, String>> errors) {
        this.errors.addAll(errors);
    }

    public List<Map<String, String>> getErrors() {
        return errors;
    }

    public void setErrors(List<Map<String, String>> errors) {
        this.errors = errors;
    }

}
