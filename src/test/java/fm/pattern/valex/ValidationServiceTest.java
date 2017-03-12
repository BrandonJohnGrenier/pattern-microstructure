package fm.pattern.valex;

import javax.validation.Validation;

import org.junit.Before;

import fm.pattern.valex.Result;
import fm.pattern.valex.SimpleValidationService;
import fm.pattern.valex.ValidationService;
import fm.pattern.valex.sequences.Create;
import fm.pattern.valex.sequences.Delete;
import fm.pattern.valex.sequences.Update;

public abstract class ValidationServiceTest {

    private ValidationService validationService;

    @Before
    public void before() {
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
    }

    public <T> ResultAssertions assertCreate(T instance) {
        Result<T> result = validationService.validate(instance, Create.class);
        return PatternAssertions.assertThat(result);
    }

    public <T> ResultAssertions assertUpdate(T instance) {
        Result<T> result = validationService.validate(instance, Update.class);
        return PatternAssertions.assertThat(result);
    }

    public <T> ResultAssertions assertDelete(T instance) {
        Result<T> result = validationService.validate(instance, Delete.class);
        return PatternAssertions.assertThat(result);
    }

}
