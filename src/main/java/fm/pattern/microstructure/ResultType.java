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

    CREATED(201), UPDATED(200), DELETED(204), OK(200), BAD_REQUEST(400, BadRequestException.class), NOT_AUTHENTICATED(401, AuthenticationException.class), NOT_AUTHORIZED(403, AuthorizationException.class), NOT_FOUND(404, EntityNotFoundException.class), CONFLICT(409, ResourceConflictException.class), UNPROCESSABLE_ENTITY(422, UnprocessableEntityException.class), INTERNAL_ERROR(500, InternalErrorException.class);

    private final Class<? extends ReportableException> exception;
    private final Integer code;

    private ResultType(Integer code) {
        this(code, null);
    }

    private ResultType(Integer code, Class<? extends ReportableException> exception) {
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
