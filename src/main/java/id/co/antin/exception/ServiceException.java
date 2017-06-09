package id.co.antin.exception;

import id.co.antin.util.StatusCode;

/**
 * Created by antin on 4/14/17.
 */
public class ServiceException extends RuntimeException {
    private StatusCode code = StatusCode.ERROR;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(StatusCode code, String message) {
        super(message);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }
}
