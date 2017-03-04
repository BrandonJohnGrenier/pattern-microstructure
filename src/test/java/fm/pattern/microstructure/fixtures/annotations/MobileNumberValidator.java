package fm.pattern.microstructure.fixtures.annotations;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fm.pattern.microstructure.ValidatorSupport;

public class MobileNumberValidator extends ValidatorSupport implements ConstraintValidator<MobileNumber, String> {

	private static final Pattern pattern = Pattern.compile("04[\\d]{8}");

	public void initialize(MobileNumber annotation) {

	}

	public boolean isValid(String value, ConstraintValidatorContext constraint) {
		return isBlank(value) ? true : pattern.matcher(value).matches();
	}

}
