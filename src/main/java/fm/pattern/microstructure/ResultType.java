package fm.pattern.microstructure;

import fm.pattern.microstructure.exceptions.AuthenticationException;
import fm.pattern.microstructure.exceptions.AuthorizationException;
import fm.pattern.microstructure.exceptions.BadRequestException;
import fm.pattern.microstructure.exceptions.EntityNotFoundException;
import fm.pattern.microstructure.exceptions.InternalErrorException;
import fm.pattern.microstructure.exceptions.ReportableException;
import fm.pattern.microstructure.exceptions.ResourceConflictException;
import fm.pattern.microstructure.exceptions.UnprocessableEntityException;

public enum ResultType {

    CREATED(201), UPDATED(200), DELETED(204), BAD_REQUEST(BadRequestException.class, 400), NOT_AUTHENTICATED(AuthenticationException.class, 401), NOT_AUTHORIZED(AuthorizationException.class, 403), NOT_FOUND(EntityNotFoundException.class, 404), CONFLICT(ResourceConflictException.class, 409), UNPROCESSABLE_ENTITY(UnprocessableEntityException.class, 422), INTERNAL_ERROR(InternalErrorException.class, 500);

    private final Class<? extends ReportableException> exception;
    private final Integer code;

    private ResultType(Integer code) {
        this(null, code);
    }

    private ResultType(Class<? extends ReportableException> exception, Integer code) {
        this.exception = exception;
        this.code = code;
    }

    public Class<? extends ReportableException> getException() {
        return exception;
    }

    public Integer getCode() {
        return code;
    }

}
