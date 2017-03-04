package fm.pattern.microstructure;

import javax.validation.Validation;

import org.junit.Before;

import fm.pattern.microstructure.Result;
import fm.pattern.microstructure.SimpleValidationService;
import fm.pattern.microstructure.ValidationService;
import fm.pattern.microstructure.sequences.Create;
import fm.pattern.microstructure.sequences.Delete;
import fm.pattern.microstructure.sequences.Update;

public class ValidationServiceTest {

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
