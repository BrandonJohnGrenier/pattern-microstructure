package fm.pattern.valex;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ReportableExceptionTest {

    private Reportable reportable;

    @Before
    public void before() {
        this.reportable = new Reportable("code", "message", null);
    }

    @Test
    public void shouldBeAbleToCreateAnAuthenticationExceptionWithASingleReportable() {
        assertThat(new AuthenticationException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateAnAuthorizationExceptionWithASingleReportable() {
        assertThat(new AuthorizationException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateAnUnprocessableEntityExceptionWithASingleReportable() {
        assertThat(new UnprocessableEntityException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateABadRequestExceptionWithASingleReportable() {
        assertThat(new BadRequestException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateAnInternalErrorExceptionWithASingleReportable() {
        assertThat(new InternalErrorException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToConvertAReportableExceptionIntoAnErrorsRepresentation() {
        ResourceConflictException exception = new ResourceConflictException(Reportable.report("contact.name.required"));

        ErrorsRepresentation representation = exception.toRepresentation();
        assertThat(representation.getErrors()).hasSize(1);

        ErrorRepresentation error = representation.getErrors().get(0);
        assertThat(error.getCode()).isEqualTo("CON-1000");
        assertThat(error.getMessage()).isEqualTo("A contact name is required.");
    }

}
