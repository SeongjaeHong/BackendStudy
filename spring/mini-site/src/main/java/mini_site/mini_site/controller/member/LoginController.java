package mini_site.mini_site.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginForm(HttpServletRequest request,
                            @RequestParam(value = "error", required = false) Boolean error,
                            @RequestParam(value = "errorMessage", required = false) String errorMessage,
                            Model model){
        String prevPage = request.getHeader("Referer");  // login 페이지로 오기 전에 있던 URL
        if (prevPage != null && !prevPage.contains("/login")) {
            request.getSession().setAttribute("prevPage", prevPage);
        }

        System.out.println("error = " + error);
        System.out.println("errorMessage = " + errorMessage);

        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("/login", "/login");
        return "login";
    }
}
