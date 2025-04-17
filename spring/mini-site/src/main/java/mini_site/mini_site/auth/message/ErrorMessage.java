package mini_site.mini_site.auth.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    LOGIN_FAILED(401, "아이디 또는 비밀번호가 틀렸습니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 계정입니다.");

    private final int status;
    private final String msg;
}
