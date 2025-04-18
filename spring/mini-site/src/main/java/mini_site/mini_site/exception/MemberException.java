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

    public MemberException(MemberExceptionMessage message) {
        super(message.toString());
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
        MEMBER_ID_NOT_FOUND("회원 ID를 찾을 수 없습니다."),
        MEMBER_LOGIN_ID_NOT_FOUND("회원 로그인 ID를 찾을 수 없습니다."),
        MEMBER_NAME_NOT_FOUND("회원 이름을 찾을 수 없습니다.");

        private final String message;

        @Override
        public String toString() {
            return message;
        }
    }
}