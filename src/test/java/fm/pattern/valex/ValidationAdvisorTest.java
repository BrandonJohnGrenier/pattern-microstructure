package fm.pattern.valex;

import static fm.pattern.valex.PatternAssertions.assertThat;
import static fm.pattern.valex.dsl.AddressDSL.address;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.validation.Validation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fm.pattern.valex.annotations.ValidationAdvisor;
import fm.pattern.valex.fixtures.model.Address;

@SuppressWarnings("unchecked")
public class ValidationAdvisorTest {

    @Mock
    private ProceedingJoinPoint pjp;

    private ValidationAdvisor advisor;
    private ValidationService validationService;

    @Before
    public void before() {
        initMocks(this);

        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
        this.advisor = new ValidationAdvisor(validationService);
    }

    @Test
    public void shouldExecuteTheUnderlyingJoinPointIfThereAreNoInputArguments() throws Throwable {
        when(pjp.getArgs()).thenReturn(new Object[] {});

        advisor.aroundCreate(pjp);
        verify(pjp, times(1)).proceed();
    }

    @Test
    public void shouldBeAbleToExecuteTheUnderlyingJoinPointWhenThereAreNoValidationErrors() throws Throwable {
        Address address = address().build();
        when(pjp.getArgs()).thenReturn(Arrays.array(address));

        advisor.aroundCreate(pjp);
        verify(pjp, times(1)).proceed();
    }

    @Test
    public void shouldBeAbleToReturnValidationErrorsIfTheInstanceToValidateIsInvalid() throws Throwable {
        Address address = address().withCountry(null).build();
        when(pjp.getArgs()).thenReturn(Arrays.array(address));

        Result<Address> result = (Result<Address>) advisor.aroundCreate(pjp);
        assertThat(result).rejected();
    }

}
