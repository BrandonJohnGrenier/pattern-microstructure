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
    public void anAcceptedResultShouldHaveATypeOfOKByDefault() {
        Result<String> result = Result.accept("Some Instance");
        assertThat(result.getType()).isEqualTo(ResultType.OK);
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
    public void aRejectedResultShouldHaveATypeOfUnprocessableEntityByDefault() {
        Result<String> result = Result.reject("error message content");
        assertThat(result.getType()).isEqualTo(ResultType.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfConflict() {
        Result<String> result = Result.conflict("error message content");
        assertThat(result.getType()).isEqualTo(ResultType.CONFLICT);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfNotFound() {
        Result<String> result = Result.not_found("error message content");
        assertThat(result.getType()).isEqualTo(ResultType.NOT_FOUND);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfNotAuthenticated() {
        Result<String> result = Result.not_authenticated("error message content");
        assertThat(result.getType()).isEqualTo(ResultType.NOT_AUTHENTICATED);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfBadRequest() {
        Result<String> result = Result.bad_request("error message content");
        assertThat(result.getType()).isEqualTo(ResultType.BAD_REQUEST);
    }

    @Test
    public void shouldBeAbleToProduceAResultWithAResultTypeOfInternalError() {
        Result<String> result = Result.internal_error("error message content");
        assertThat(result.getType()).isEqualTo(ResultType.INTERNAL_ERROR);
    }

    @Test
    public void shouldBeAbleToRaiseAnUnprocessableEntityException() {
        Result<String> result = Result.reject("error message content");
        UnprocessableEntityException exception = (UnprocessableEntityException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("error message content");
    }

    @Test
    public void shouldBeAbleToRaiseAResourceConflictException() {
        Result<String> result = Result.conflict("error message content");
        ResourceConflictException exception = (ResourceConflictException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("error message content");
    }

    @Test
    public void shouldBeAbleToRaiseABadRequestException() {
        Result<String> result = Result.bad_request("error message content");
        BadRequestException exception = (BadRequestException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("error message content");
    }

    @Test
    public void shouldBeAbleToRaiseAnAuthenticationException() {
        Result<String> result = Result.not_authenticated("error message content");
        AuthenticationException exception = (AuthenticationException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("error message content");
    }

    @Test
    public void shouldBeAbleToRaiseAnAuthorizationException() {
        Result<String> result = Result.not_authorized("error message content");
        AuthorizationException exception = (AuthorizationException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("error message content");
    }

    @Test
    public void shouldBeAbleToRaiseAnEntityNotFoundException() {
        Result<String> result = Result.not_found("error message content");
        EntityNotFoundException exception = (EntityNotFoundException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("error message content");
    }

    @Test
    public void shouldBeAbleToRaiseAnInternalErrorException() {
        Result<String> result = Result.internal_error("error message content");
        InternalErrorException exception = (InternalErrorException) result.raise();

        assertThat(exception.getErrors()).hasSize(1);
        assertThat(exception.getErrors().get(0).getMessage()).isEqualTo("error message content");
    }

    @Test
    public void shouldNotRaiseAnExceptionIfTheResultTypeDoesNotHaveAnException() {
        Result<String> result = Result.accept("All good");
        assertThat(result.raise()).isNull();
    }

    @Test
    public void shouldBeAbleToSubstituteArgumentsInAnErrorMessage() {
        Result<String> result = Result.reject("The username %s cannot be greater than %d characters.", "smithers", 5);
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("The username smithers cannot be greater than 5 characters.");
    }

    @Test
    public void shouldBeAbleToResolveACodeAndMessageForAnErrorKey() {
        Result<String> result = Result.reject("contact.name.required");
        assertThat(result.getErrors().get(0).getCode()).isEqualTo("CON-1000");
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("A contact name is required.");
    }

    @Test
    public void shouldBeAbleToResolveACodeAndMessageAndSubstituteArgumentsForAnErrorKey() {
        Result<String> result = Result.reject("username.length", "jim", 2);
        assertThat(result.getErrors().get(0).getCode()).isEqualTo("LOC-NTFD");
        assertThat(result.getErrors().get(0).getMessage()).isEqualTo("The username jim cannot be greater than 2 characters.");
    }

}
