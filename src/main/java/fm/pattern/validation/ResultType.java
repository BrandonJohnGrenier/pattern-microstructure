package fm.pattern.validation;

import fm.pattern.validation.exceptions.AuthenticationException;
import fm.pattern.validation.exceptions.AuthorizationException;
import fm.pattern.validation.exceptions.BadRequestException;
import fm.pattern.validation.exceptions.EntityNotFoundException;
import fm.pattern.validation.exceptions.InternalErrorException;
import fm.pattern.validation.exceptions.UnprocessableEntityException;

public enum ResultType {

    CREATED(201),
    UPDATED(200),
    DELETED(204),
    BAD_REQUEST(BadRequestException.class, 400),
    NOT_AUTHENTICATED(AuthenticationException.class, 401),
    NOT_AUTHORIZED(AuthorizationException.class, 403),
    NOT_FOUND(EntityNotFoundException.class, 404),
    CONFLICT(UnprocessableEntityException.class, 409),
    UNPROCESSABLE_ENTITY(UnprocessableEntityException.class, 422),
    INTERNAL_ERROR(InternalErrorException.class, 500);

    private final Class<?> exception;
    private final Integer code;

    private ResultType(Integer code) {
        this(null, code);
    }
    
    private ResultType(Class<?> exception, Integer code) {
        this.exception = exception;
        this.code = code;
    }

    public Class<?> getException() {
        return exception;
    }

    public Integer getCode() {
        return code;
    }

}
