package fm.pattern.microstructure;

import org.assertj.core.api.Assertions;

import fm.pattern.microstructure.Result;

public class PlatformAssertions extends Assertions {

	public static ResultAssertions assertThat(Result<?> result) {
		return new ResultAssertions(result);
	}

}
