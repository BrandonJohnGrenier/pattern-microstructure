package fm.pattern.valex.annotations;

import static fm.pattern.valex.PatternAssertions.assertThat;
import static fm.pattern.valex.dsl.AddressDSL.address;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.lang.annotation.Annotation;

import javax.validation.Validation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fm.pattern.valex.Result;
import fm.pattern.valex.SimpleValidationService;
import fm.pattern.valex.UnprocessableEntityException;
import fm.pattern.valex.ValidationService;
import fm.pattern.valex.fixtures.model.Address;

@SuppressWarnings("all")
public class ValidationAdvisorTest {

    @Mock
    private ProceedingJoinPoint pjp;

    private ValidationAdvisor advisor;
    private ValidationService validationService;

    private CreateAnnotation create;

    @Before
    public void before() {
        initMocks(this);
        this.create = new CreateAnnotation();
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
        this.advisor = new ValidationAdvisor(validationService);
    }

    @Test
    public void shouldExecuteTheUnderlyingCreateJoinPointIfThereAreNoInputArguments() throws Throwable {
        when(pjp.getArgs()).thenReturn(new Object[] {});

        advisor.aroundCreate(pjp, create);
        verify(pjp, times(1)).proceed();
    }

    @Test
    public void shouldBeAbleToExecuteTheUnderlyingCreateJoinPointWhenThereAreNoValidationErrors() throws Throwable {
        Address address = address().build();
        when(pjp.getArgs()).thenReturn(Arrays.array(address));

        advisor.aroundCreate(pjp, create);
        verify(pjp, times(1)).proceed();
    }

    @Test
    public void shouldBeAbleToReturnValidationErrorsOnCreateIfTheInstanceToValidateIsInvalid() throws Throwable {
        Address address = address().withCountry(null).build();
        when(pjp.getArgs()).thenReturn(Arrays.array(address));

        Result<Address> result = (Result<Address>) advisor.aroundCreate(pjp, create);
        assertThat(result).rejected();
    }

    @Test(expected = UnprocessableEntityException.class)
    public void shouldBeAbleToThrowAnExceptionOnCreateIfTheInstanceToValidateIsInvalid() throws Throwable {
        Address address = address().withCountry(null).build();
        when(pjp.getArgs()).thenReturn(Arrays.array(address));

        create.setThrowException(true);

        Result<Address> result = (Result<Address>) advisor.aroundCreate(pjp, create);
        assertThat(result).rejected();
    }

    public class CreateAnnotation implements Create {

        private boolean throwException = false;

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }

        @Override
        public boolean throwException() {
            return throwException;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return CreateAnnotation.class;
        }

    }

}
