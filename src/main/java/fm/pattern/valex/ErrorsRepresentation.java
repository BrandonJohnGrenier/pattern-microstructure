package fm.pattern.valex;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorsRepresentation {

    private List<ErrorRepresentation> errors = new ArrayList<ErrorRepresentation>();

    public ErrorsRepresentation() {

    }

    public ErrorsRepresentation(List<ErrorRepresentation> errors) {
        this.errors.addAll(errors);
    }

    public List<ErrorRepresentation> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorRepresentation> errors) {
        this.errors = errors;
    }

}
