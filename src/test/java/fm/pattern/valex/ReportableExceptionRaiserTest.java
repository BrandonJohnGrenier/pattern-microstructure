package fm.pattern.valex;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ReportableExceptionRaiserTest {

    @Test
    public void shouldBeAbleToRaiseAReportableExceptionPopulatedWithTheGivenReportableErrors() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("code1", "message1", EntityNotFoundException.class));
        errors.add(new Reportable("code2", "message2", EntityNotFoundException.class));

        ReportableException exception = ReportableExceptionRaiser.raise(errors);
        Assertions.assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        Assertions.assertThat(exception.getErrors()).hasSize(2);

        Assertions.assertThat(exception.getErrors().get(0).getCode()).isEqualTo("code1");
        Assertions.assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("message1");
        Assertions.assertThat(exception.getErrors().get(1).getCode()).isEqualTo("code2");
        Assertions.assertThat(exception.getErrors().get(1).getMessage()).isEqualTo("message2");
    }

    @Test
    public void shouldBeAbleToRaiseAReportableExceptionPopulatedWithTheGivenReportableErrorsAndIgnoreNullReportables() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(null);
        errors.add(new Reportable("code1", "message1", InternalErrorException.class));
        errors.add(null);
        errors.add(new Reportable("code2", "message2", InternalErrorException.class));
        errors.add(null);

        ReportableException exception = ReportableExceptionRaiser.raise(errors);
        Assertions.assertThat(exception).isInstanceOf(InternalErrorException.class);
        Assertions.assertThat(exception.getErrors()).hasSize(2);

        Assertions.assertThat(exception.getErrors().get(0).getCode()).isEqualTo("code1");
        Assertions.assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("message1");
        Assertions.assertThat(exception.getErrors().get(1).getCode()).isEqualTo("code2");
        Assertions.assertThat(exception.getErrors().get(1).getMessage()).isEqualTo("message2");
    }

    @Test
    public void shouldBeAbleToOverrideReportableExceptionPopulatedWithTheGivenReportableErrors() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("code1", "message1", BadRequestException.class));
        errors.add(new Reportable("code2", "message2", BadRequestException.class));

        ReportableException exception = ReportableExceptionRaiser.raise(AuthorizationException.class, errors);
        Assertions.assertThat(exception).isInstanceOf(AuthorizationException.class);
        Assertions.assertThat(exception.getErrors()).hasSize(2);

        Assertions.assertThat(exception.getErrors().get(0).getCode()).isEqualTo("code1");
        Assertions.assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("message1");
        Assertions.assertThat(exception.getErrors().get(1).getCode()).isEqualTo("code2");
        Assertions.assertThat(exception.getErrors().get(1).getMessage()).isEqualTo("message2");
    }

    @Test
    public void shouldReturnAnUnprocessableEntityExceptionIfTheProvidedExceptionIsNull() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("code1", "message1", BadRequestException.class));
        errors.add(new Reportable("code2", "message2", BadRequestException.class));

        ReportableException exception = ReportableExceptionRaiser.raise(null, errors);
        Assertions.assertThat(exception).isInstanceOf(UnprocessableEntityException.class);
    }

    @Test
    public void shouldReturnAnUnprocessableEntityExceptionIfTheReportablesDoNotHaveExceptions() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("code1", "message1", null));
        errors.add(new Reportable("code2", "message2", null));

        ReportableException exception = ReportableExceptionRaiser.raise(null, errors);
        Assertions.assertThat(exception).isInstanceOf(UnprocessableEntityException.class);
    }

    @Test
    public void shouldFilterOutAnyReportablesThatDoNotHaveExceptions() {
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("code1", "message1", null));
        errors.add(new Reportable("code2", "message2", BadRequestException.class));

        ReportableException exception = ReportableExceptionRaiser.raise(AuthenticationException.class, errors);
        Assertions.assertThat(exception).isInstanceOf(AuthenticationException.class);
        Assertions.assertThat(exception.getErrors()).hasSize(2);

        Assertions.assertThat(exception.getErrors().get(0).getCode()).isEqualTo("code1");
        Assertions.assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("message1");
        Assertions.assertThat(exception.getErrors().get(1).getCode()).isEqualTo("code2");
        Assertions.assertThat(exception.getErrors().get(1).getMessage()).isEqualTo("message2");
    }

    @Test
    public void theClassShouldBeAWellDefinedUtilityClass() {
        PatternAssertions.assertClass(ReportableExceptionRaiser.class).isAWellDefinedUtilityClass();
    }

}
