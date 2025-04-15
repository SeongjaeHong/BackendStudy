package mini_site.mini_site.exception;

import lombok.RequiredArgsConstructor;

public class MemberException extends RuntimeException {
    public MemberException() {
        super();
    }

    public MemberException(String message) {
        super(message);
    }

    public MemberException(MemberExceptionMessage message, String info) {
        super(message + "(" + info + ")");
    }

    public MemberException(MemberExceptionMessage message, Long info) {
        super(message + "(" + info + ")");
    }

    public MemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberException(Throwable cause) {
        super(cause);
    }

    protected MemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @RequiredArgsConstructor
    public enum MemberExceptionMessage {
        USER_ID_NOT_FOUND("User id is not found."),
        USER_LOGIN_ID_NOT_FOUND("User login-id is not found."),
        USER_NAME_NOT_FOUND("User name is not found.");

        private final String message;
    }
}