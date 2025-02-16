package exception;

public class QuantityLessThanZeroException extends Exception{
    public QuantityLessThanZeroException() {
    }

    public QuantityLessThanZeroException(String message) {
        super(message);
    }

    public QuantityLessThanZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuantityLessThanZeroException(Throwable cause) {
        super(cause);
    }

    public QuantityLessThanZeroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
