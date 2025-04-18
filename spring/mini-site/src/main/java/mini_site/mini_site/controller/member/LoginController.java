package mini_site.mini_site.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginForm(HttpServletRequest request,
                            Model model){
        String prevPage = request.getHeader("Referer");  // login 페이지로 오기 전에 있던 URL
        if (prevPage != null && !prevPage.contains("/login")) {
            request.getSession().setAttribute("prevPage", prevPage);
        }

        HttpSession session = request.getSession();

        model.addAttribute("error", session.getAttribute("error"));
        model.addAttribute("errorMessage", session.getAttribute("errorMessage"));
        model.addAttribute("login", "/login");
        model.addAttribute("signup", "/signup");

        if (session.getAttribute("error") != null) {
            request.getSession().removeAttribute("error");
            request.getSession().removeAttribute("errorMessage");
        }

        return "login";
    }
}
