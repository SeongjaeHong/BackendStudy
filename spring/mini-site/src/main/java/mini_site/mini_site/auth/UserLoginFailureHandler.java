package mini_site.mini_site.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mini_site.mini_site.auth.message.ErrorMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class UserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ErrorMessage errorMessage;
        if (exception instanceof UsernameNotFoundException) {
            errorMessage = ErrorMessage.MEMBER_NOT_FOUND;
        } else {
            errorMessage = ErrorMessage.LOGIN_FAILED;
        }

        setDefaultFailureUrl("/login?error=true&errorMessage=" + errorMessage.getMsg());
        super.onAuthenticationFailure(request, response, exception);
    }
}
