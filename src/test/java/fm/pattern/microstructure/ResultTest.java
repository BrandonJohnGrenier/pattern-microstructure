package fm.pattern.microstructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import fm.pattern.microstructure.exceptions.AuthenticationException;
import fm.pattern.microstructure.exceptions.AuthorizationException;
import fm.pattern.microstructure.exceptions.BadRequestException;
import fm.pattern.microstructure.exceptions.EntityNotFoundException;
import fm.pattern.microstructure.exceptions.InternalErrorException;
import fm.pattern.microstructure.exceptions.ResourceConflictException;
import fm.pattern.microstructure.exceptions.UnprocessableEntityException;

public class ResultTest {

    @Test
    public void anAcceptedResultShouldHaveNotTypeByDefault() {
        Result<String> result = Result.accept("Some Instance");
        assertThat(result.getType()).isNull();
    }

    @Test
    public void aRejectedResultShouldHaveATypeOfUnprocessableEntityByDefault() {
        Result<String> result = Result.reject("Some Instance");
        assertThat(result.getType()).isEqualTo(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfCreated() {
        Result<String> result = Result.created("Some Instance");
        assertThat(result.getType()).isEqualTo(ResultType.CREATED);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfUpdated() {
        Result<String> result = Result.updated("Some Instance");
        assertThat(result.getType()).isEqualTo(ResultType.UPDATED);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfDeleted() {
        Result<String> result = Result.deleted("Some Instance");
        assertThat(result.getType()).isEqualTo(ResultType.DELETED);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfConflict() {
        Result<String> result = Result.conflict("Resource already exists.");
        assertThat(result.getType()).isEqualTo(ResultType.CONFLICT);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfNotFound() {
        Result<String> result = Result.not_found("Resource not found.");
        assertThat(result.getType()).isEqualTo(ResultType.NOT_FOUND);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfNotAuthenticated() {
        Result<String> result = Result.not_authenticated("Full authentication required to access this resource.");
        assertThat(result.getType()).isEqualTo(ResultType.NOT_AUTHENTICATED);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfBadRequest() {
        Result<String> result = Result.bad_request("Document is invalid.");
        assertThat(result.getType()).isEqualTo(ResultType.BAD_REQUEST);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfInternalError() {
        Result<String> result = Result.internal_error("Unable to process your request.");
        assertThat(result.getType()).isEqualTo(ResultType.INTERNAL_ERROR);
    }

    @Test
    public void shouldBeAbleToRaiseAnUnprocessableEntityException() {
        Result<String> result = Result.reject("An error description");
        UnprocessableEntityException exception = (UnprocessableEntityException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getDescription()).isEqualTo("An error description");
    }

    @Test
    public void shouldBeAbleToRaiseAResourceConflictException() {
        Result<String> result = Result.conflict("An error description");
        ResourceConflictException exception = (ResourceConflictException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getDescription()).isEqualTo("An error description");
    }

    @Test
    public void shouldBeAbleToRaiseABadRequestException() {
        Result<String> result = Result.bad_request("An error description");
        BadRequestException exception = (BadRequestException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getDescription()).isEqualTo("An error description");
    }

    @Test
    public void shouldBeAbleToRaiseAnAuthenticationException() {
        Result<String> result = Result.not_authenticated("An error description");
        AuthenticationException exception = (AuthenticationException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getDescription()).isEqualTo("An error description");
    }

    @Test
    public void shouldBeAbleToRaiseAnAuthorizationException() {
        Result<String> result = Result.not_authorized("An error description");
        AuthorizationException exception = (AuthorizationException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getDescription()).isEqualTo("An error description");
    }

    @Test
    public void shouldBeAbleToRaiseAnEntityNotFoundException() {
        Result<String> result = Result.not_found("An error description");
        EntityNotFoundException exception = (EntityNotFoundException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getDescription()).isEqualTo("An error description");
    }

    @Test
    public void shouldBeAbleToRaiseAnInternalErrorException() {
        Result<String> result = Result.internal_error("An error description");
        InternalErrorException exception = (InternalErrorException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getDescription()).isEqualTo("An error description");
    }

    @Test
    public void shouldNotRaiseAnExceptionIfTheResultTypeDoesNotHaveAnException() {
        Result<String> result = Result.accept("All good");
        assertThat(result.raise()).isNull();
    }

}
