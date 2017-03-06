package fm.pattern.validation;

import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(new AuthenticationException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateAnAuthorizationExceptionWithASingleReportable() {
        Assertions.assertThat(new AuthorizationException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateAnUnprocessableEntityExceptionWithASingleReportable() {
        Assertions.assertThat(new UnprocessableEntityException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateABadRequestExceptionWithASingleReportable() {
        Assertions.assertThat(new BadRequestException(reportable).getErrors()).contains(reportable);
    }

    @Test
    public void shouldBeAbleToCreateAnInternalErrorExceptionWithASingleReportable() {
        Assertions.assertThat(new InternalErrorException(reportable).getErrors()).contains(reportable);
    }

}
