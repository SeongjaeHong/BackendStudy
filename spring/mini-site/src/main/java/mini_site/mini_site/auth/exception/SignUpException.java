package mini_site.mini_site.auth.exception;


import mini_site.mini_site.auth.message.SignUpMessage;

public class SignUpException extends RuntimeException {
    public SignUpException(String message) {
        super(message);
    }

    public SignUpException(SignUpMessage message) {
        super(message.toString());
    }
}
