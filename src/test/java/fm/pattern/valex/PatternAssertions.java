package fm.pattern.valex;

import org.assertj.core.api.Assertions;

public class PatternAssertions extends Assertions {

    public static ResultAssertions assertThat(Result<?> result) {
        return new ResultAssertions(result);
    }

    public static UtilityClassAssertions assertClass(Class<?> clazz) {
        return new UtilityClassAssertions(clazz);
    }

}
