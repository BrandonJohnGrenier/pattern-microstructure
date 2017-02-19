package fm.pattern.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

	private List<String> messages = new ArrayList<String>();
	private boolean authorizationErrors = false;

	public ValidationResult() {

	}

	public ValidationResult(List<String> messages) {
		this.messages.addAll(messages);
	}

	public List<String> getMessages() {
		return messages;
	}

	public ValidationResult withMessages(List<String> messages) {
		this.messages.addAll(messages);
		return this;
	}

	public <T> Result<T> reject(T instance) {
		return Result.reject(instance, getMessages());
	}

	public boolean containsErrors() {
		return !messages.isEmpty();
	}

	public void authorizationErrorDetected() {
		this.authorizationErrors = true;
	}

	public boolean containsAuthorizationErrors() {
		return authorizationErrors;
	}

}
