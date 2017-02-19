package fm.pattern.validation;

import javax.validation.Validation;

import org.junit.Before;

import fm.pattern.validation.sequence.Create;
import fm.pattern.validation.sequence.Update;

public class ValidationTest {

    private ValidationService validationService;

    @Before
    public void before() {
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
    }
    
    public <T> ResultAssertions create(T instance) {
        Result<T> result = validationService.validate(instance, Create.class);
        return PlatformAssertions.assertThat(result);
    }

    public <T> ResultAssertions update(T instance) {
        Result<T> result = validationService.validate(instance, Update.class);
        return PlatformAssertions.assertThat(result);
    }
    
}
