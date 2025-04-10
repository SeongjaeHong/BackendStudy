package mini_site.mini_site.exception;

public class PostException extends RuntimeException {
    public PostException(String message) {
        super(message);
    }

    public PostException() {
        super();
    }

    public PostException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostException(Throwable cause) {
        super(cause);
    }

    protected PostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
