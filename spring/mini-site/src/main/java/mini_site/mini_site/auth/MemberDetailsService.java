package mini_site.mini_site.auth;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.exception.MemberException;
import mini_site.mini_site.service.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member;
        try {
            member = memberService.findMemberByLoginId(loginId);
        } catch (MemberException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        return User.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
                .roles(String.valueOf(member.getMemberLevel()))
                .build();
    }
}
