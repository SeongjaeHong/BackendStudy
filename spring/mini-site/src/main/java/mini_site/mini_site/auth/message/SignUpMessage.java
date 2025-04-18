package mini_site.mini_site.auth.message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SignUpMessage {
    ID_OK("사용 가능한 아이디입니다."),
    NAME_OK("사용 가능한 이름입니다."),
    PASSWORD_OK("사용 가능한 비밀번호입니다."),
    ID_DUPLICATED("이미 존재하는 ID입니다."),
    NAME_DUPLICATED("이미 존재하는 이름입니다.");

    private final String message;

    @Override
    public String toString() {
        return message;
    }
}
