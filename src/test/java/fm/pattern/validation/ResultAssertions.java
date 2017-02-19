package fm.pattern.validation;

import java.util.Arrays;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ResultAssertions extends AbstractAssert<ResultAssertions, Result<?>> {

    public ResultAssertions(Result<?> result) {
        super(result, ResultAssertions.class);
    }

    public static ResultAssertions assertThat(Result<?> result) {
        return new ResultAssertions(result);
    }

    public ResultAssertions and() {
        return this;
    }

    public ResultAssertions accepted() {
        Assertions.assertThat(actual.accepted()).describedAs("Expected the entity to be accepted, but had " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isTrue();
        return this;
    }

    public ResultAssertions rejected() {
        Assertions.assertThat(actual.rejected()).describedAs("Expected the entity to be rejected, but had " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isTrue();
        return this;
    }

    public ResultAssertions withErrors(String... errors) {
        Assertions.assertThat(errors.length).describedAs("Expected " + errors.length + " errors but found " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isEqualTo(actual.getErrors().size());
        for (String error : errors) {
            Assertions.assertThat(actual.getErrors()).extracting("description").contains(error);
        }
        return this;
    }

}
