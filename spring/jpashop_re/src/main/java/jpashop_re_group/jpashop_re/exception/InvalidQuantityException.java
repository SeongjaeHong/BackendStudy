package jpashop_re_group.jpashop_re.exception;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super();
    }

    public InvalidQuantityException(String message) {
        super(message);
    }

    public InvalidQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidQuantityException(Throwable cause) {
        super(cause);
    }

    protected InvalidQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
