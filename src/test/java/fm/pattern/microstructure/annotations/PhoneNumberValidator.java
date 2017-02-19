package fm.pattern.microstructure.annotations;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fm.pattern.microstructure.ValidatorSupport;

public class PhoneNumberValidator extends ValidatorSupport implements ConstraintValidator<PhoneNumber, String> {

	private static final Pattern pattern = Pattern.compile("0[2,3,7,8][\\d]{8}");

	public void initialize(PhoneNumber annotation) {

	}

	public boolean isValid(String value, ConstraintValidatorContext constraint) {
		return isBlank(value) ? true : pattern.matcher(value).matches();
	}

}
