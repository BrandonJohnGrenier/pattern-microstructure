package fm.pattern.validation;

import org.assertj.core.api.Assertions;

public class PlatformAssertions extends Assertions {

	public static ResultAssertions assertThat(Result<?> result) {
		return new ResultAssertions(result);
	}

}
