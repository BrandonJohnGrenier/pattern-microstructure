package fm.pattern.valex;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.commons.util.JSON;
import fm.pattern.valex.config.ValexConfiguration;
import fm.pattern.valex.config.YamlConfigurationFile;

public class ReportableExceptionTest {

    private Reportable reportable;

    @Before
    public void before() {
        ValexConfiguration.use(new YamlConfigurationFile());
        this.reportable = new Reportable("code", "message");
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
        List<Reportable> errors = new ArrayList<>();
        errors.add(new Reportable("contact.name.required"));
        errors.add(new Reportable("address.city.size"));

        ResourceConflictException exception = new ResourceConflictException(errors);

        ErrorsRepresentation representation = exception.toRepresentation();
        assertThat(representation.getErrors()).hasSize(2);

        System.out.println(JSON.stringify(representation));

        Map<String, String> first = representation.getErrors().get(0);
        assertThat(first.get("code")).isEqualTo("CON-1000");
        assertThat(first.get("message")).isEqualTo("A contact name is required.");

        Map<String, String> second = representation.getErrors().get(1);
        assertThat(second.get("code")).isEqualTo("ADD-1000");
        assertThat(second.get("message")).isEqualTo("The unit number cannot be greater than 10 characters.");
    }

}
