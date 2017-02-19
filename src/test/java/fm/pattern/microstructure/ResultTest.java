package fm.pattern.microstructure;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

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

}
