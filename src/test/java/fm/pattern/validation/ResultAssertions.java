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

    public ResultAssertions withType(ResultType type) {
        Assertions.assertThat(actual.getType()).isEqualTo(type);
        return this;
    }

    public ResultAssertions withCode(String... codes) {
        Assertions.assertThat(codes.length).describedAs("Expected " + codes.length + " error codes but found " + actual.getErrors().size() + " instead: " + Arrays.toString(actual.getErrors().toArray())).isEqualTo(actual.getErrors().size());
        for (String code : codes) {
            Assertions.assertThat(actual.getErrors()).extracting("code").contains(code);
        }
        return this;
    }

    public ResultAssertions withDescription(String... descriptions) {
        Assertions.assertThat(descriptions.length).describedAs("Expected " + descriptions.length + " error descriptions but found " + actual.getErrors().size() + " instead: " + Arrays.toString(actual.getErrors().toArray())).isEqualTo(actual.getErrors().size());
        for (String error : descriptions) {
            Assertions.assertThat(actual.getErrors()).extracting("description").contains(error);
        }
        return this;
    }

}
