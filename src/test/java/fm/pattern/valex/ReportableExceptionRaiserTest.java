package fm.pattern.valex;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import fm.pattern.valex.config.ValexConfiguration;

public class ReportableExceptionRaiserTest {

    @Before
    public void before() {
        ValexConfiguration.use(ValexConfiguration.YAML_FILE);
    }
    
    @Test
    public void shouldBeAbleToRaiseAReportableExceptionPopulatedWithTheGivenReportableErrors() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("not.found.one"));
        errors.add(new Reportable("not.found.two"));

        ReportableException exception = ReportableExceptionRaiser.raise(errors);
        Assertions.assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        Assertions.assertThat(exception.getErrors()).hasSize(2);

        Assertions.assertThat(exception.getErrors().get(0).getCode()).isEqualTo("FR-1000");
        Assertions.assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("Not found 1.");

        Assertions.assertThat(exception.getErrors().get(1).getCode()).isEqualTo("FR-1001");
        Assertions.assertThat(exception.getErrors().get(1).getMessage()).isEqualTo("Not found 2.");
    }

    @Test
    public void shouldBeAbleToRaiseAReportableExceptionPopulatedWithTheGivenReportableErrorsAndIgnoreNullReportables() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(null);
        errors.add(new Reportable("internal.error.one"));
        errors.add(null);
        errors.add(new Reportable("internal.error.two"));
        errors.add(null);

        ReportableException exception = ReportableExceptionRaiser.raise(errors);
        Assertions.assertThat(exception).isInstanceOf(InternalErrorException.class);
        Assertions.assertThat(exception.getErrors()).hasSize(2);

        Assertions.assertThat(exception.getErrors().get(0).getCode()).isEqualTo("ER-1000");
        Assertions.assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("Internal error 1.");

        Assertions.assertThat(exception.getErrors().get(1).getCode()).isEqualTo("ER-1001");
        Assertions.assertThat(exception.getErrors().get(1).getMessage()).isEqualTo("Internal error 2.");
    }

    @Test
    public void shouldBeAbleToOverrideReportableExceptionPopulatedWithTheGivenReportableErrors() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("not.found.one"));
        errors.add(new Reportable("not.found.two"));

        ReportableException exception = ReportableExceptionRaiser.raise(AuthorizationException.class, errors);
        Assertions.assertThat(exception).isInstanceOf(AuthorizationException.class);
        Assertions.assertThat(exception.getErrors()).hasSize(2);

        Assertions.assertThat(exception.getErrors().get(0).getCode()).isEqualTo("FR-1000");
        Assertions.assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("Not found 1.");

        Assertions.assertThat(exception.getErrors().get(1).getCode()).isEqualTo("FR-1001");
        Assertions.assertThat(exception.getErrors().get(1).getMessage()).isEqualTo("Not found 2.");
    }

    @Test
    public void shouldReturnAnUnprocessableEntityExceptionIfTheProvidedExceptionIsNull() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("bad.request.one"));
        errors.add(new Reportable("bad.reqeust.two"));

        ReportableException exception = ReportableExceptionRaiser.raise(null, errors);
        Assertions.assertThat(exception).isInstanceOf(UnprocessableEntityException.class);
    }

    @Test
    public void theClassShouldBeAWellDefinedUtilityClass() {
        PatternAssertions.assertClass(ReportableExceptionRaiser.class).isAWellDefinedUtilityClass();
    }

}
