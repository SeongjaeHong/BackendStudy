package mini_site.mini_site.controller.member;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.exception.MemberException;
import mini_site.mini_site.service.member.MemberService;
import mini_site.mini_site.service.member.dto.request.RegisterMemberRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("signup", "/signup");
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute RegisterMemberRequest registerMemberRequest) {
        try {
            memberService.registerMember(registerMemberRequest);
        } catch (MemberException e) {
            return "redirect:/signup";
        }
        return "redirect:/";
    }
}
