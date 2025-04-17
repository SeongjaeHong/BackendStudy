package mini_site.mini_site.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
