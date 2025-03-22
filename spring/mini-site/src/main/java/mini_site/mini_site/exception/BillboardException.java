package mini_site.mini_site.exception;

public class BillboardException extends RuntimeException {
  public BillboardException() {
    super();
  }

  public BillboardException(String message) {
    super(message);
  }

  public BillboardException(String message, Throwable cause) {
    super(message, cause);
  }

  public BillboardException(Throwable cause) {
    super(cause);
  }

  protected BillboardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
