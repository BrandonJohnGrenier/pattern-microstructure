package fm.pattern.validation;

import javax.validation.Validation;

import org.junit.Before;

import fm.pattern.validation.Result;
import fm.pattern.validation.SimpleValidationService;
import fm.pattern.validation.ValidationService;
import fm.pattern.validation.sequences.Create;
import fm.pattern.validation.sequences.Delete;
import fm.pattern.validation.sequences.Update;

public abstract class ValidationServiceTest {

    private ValidationService validationService;

    @Before
    public void before() {
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
    }

    public <T> ResultAssertions assertCreate(T instance) {
        Result<T> result = validationService.validate(instance, Create.class);
        return PlatformAssertions.assertThat(result);
    }

    public <T> ResultAssertions assertUpdate(T instance) {
        Result<T> result = validationService.validate(instance, Update.class);
        return PlatformAssertions.assertThat(result);
    }

    public <T> ResultAssertions assertDelete(T instance) {
        Result<T> result = validationService.validate(instance, Delete.class);
        return PlatformAssertions.assertThat(result);
    }

}
