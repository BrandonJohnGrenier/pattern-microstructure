package fm.pattern.validation;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result<T> {

	private final T instance;
	private final List<String> errors;
	private final String errorType;

	public static <T> Result<T> accept(T instance) {
		return new Result<T>(instance, null, new ArrayList<>());
	}

	public static <T> Result<T> reject(T instance, List<String> errors) {
		return new Result<T>(instance, "validation", errors);
	}

	public static <T> Result<T> reject(List<String> errors) {
		return new Result<T>(null, "validation", errors);
	}

	public static <T> Result<T> reject(T instance, String error) {
		return new Result<T>(instance, "validation", Arrays.asList(resolve(error)));
	}

	public static <T> Result<T> reject(String error) {
		return new Result<T>(null, "validation", Arrays.asList(resolve(error)));
	}

	public static <T> Result<T> notFound(String error) {
		return new Result<T>(null, "resource", Arrays.asList(resolve(error)));
	}

	public static <T> Result<T> unauthorized(String error) {
		return new Result<T>(null, "authorization", Arrays.asList(resolve(error)));
	}

	public static <T> Result<T> authenticationRequired(String error) {
		return new Result<T>(null, "authentication", Arrays.asList(resolve(error)));
	}

	public static <T> Result<T> conflict(String error) {
		return new Result<T>(null, "conflict", Arrays.asList(resolve(error)));
	}

	private Result(T instance, String errorType, List<String> errors) {
		this.instance = instance;
		this.errorType = errorType;
		this.errors = errors;
	}

	public T getInstance() {
		return instance;
	}

	public boolean accepted() {
		return getErrors().isEmpty();
	}

	public boolean rejected() {
		return !getErrors().isEmpty();
	}

	public boolean hasAuthorizationError() {
		return errorType == null ? false : errorType.equals("authorization");
	}

	public boolean hasAuthenticationError() {
		return errorType == null ? false : errorType.equals("authentication");
	}

	public boolean hasValidationError() {
		return errorType == null ? false : errorType.equals("validation");
	}

	public boolean hasResourceError() {
		return errorType == null ? false : errorType.equals("resource");
	}

	public boolean hasConflictError() {
		return errorType == null ? false : errorType.equals("conflict");
	}

	public String getErrorType() {
		return errorType;
	}

	public List<String> getErrors() {
		return errors != null ? errors : new ArrayList<>();
	}

	private static String resolve(String error) {
		if (isBlank(error)) {
			return error;
		}
		if (error.startsWith("{") && error.endsWith("}")) {
			return ValidationMessages.getMessage(error.replace("{", "").replace("}", ""));
		}
		return error;
	}

}
