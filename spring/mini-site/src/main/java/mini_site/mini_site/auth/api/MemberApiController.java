package mini_site.mini_site.auth.api;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.auth.message.SignUpMessage;
import mini_site.mini_site.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/check-loginId")
    public ResponseEntity<DuplicateCheckResponse> checkLoginIdDuplication(@RequestParam String loginId) {
        boolean isDuplicate = memberService.findMemberByLoginId(loginId).isPresent();
        String message = isDuplicate? SignUpMessage.ID_DUPLICATED.toString() : SignUpMessage.ID_OK.toString();
        return ResponseEntity.ok(new DuplicateCheckResponse(isDuplicate, message));
    }

    @GetMapping("/check-name")
    public ResponseEntity<DuplicateCheckResponse> checkNameDuplication(@RequestParam String name) {
        boolean isDuplicate = memberService.findMemberByName(name).isPresent();
        String message = isDuplicate? SignUpMessage.NAME_DUPLICATED.toString() : SignUpMessage.NAME_OK.toString();
        return ResponseEntity.ok(new DuplicateCheckResponse(isDuplicate, message));
    }
}
