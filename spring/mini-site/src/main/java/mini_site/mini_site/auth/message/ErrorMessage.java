package mini_site.mini_site.auth.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    LOGIN_FAILED(401, "Id or password is invalid."),
    MEMBER_NOT_FOUND(404, "Id not exists.");

    private final int status;
    private final String msg;

    public String getMsg() throws UnsupportedEncodingException {
        return URLEncoder.encode(msg, StandardCharsets.UTF_8);
    }
}
