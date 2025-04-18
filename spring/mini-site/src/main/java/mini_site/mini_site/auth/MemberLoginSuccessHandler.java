package mini_site.mini_site.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

public class MemberLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        HttpSession session = request.getSession();
        if(session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

        if(savedRequest == null) { // User entered a login page via clicking a login button
            Object prevPage = request.getSession().getAttribute("prevPage");
            String redirectPage;
            if (prevPage != null) {
                redirectPage = prevPage.toString();
            } else {
                redirectPage = "/";
            }

            redirectStrategy.sendRedirect(request, response, redirectPage);
        } else {  // User got redirected by Spring Security because they tried to enter a route without access authority
            redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        }
    }
}
