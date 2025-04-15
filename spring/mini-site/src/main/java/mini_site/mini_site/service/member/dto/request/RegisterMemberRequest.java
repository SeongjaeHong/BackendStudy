package mini_site.mini_site.service.member.dto.request;

import mini_site.mini_site.domain.member.Member;

public record RegisterMemberRequest(
        String loginId,
        String name,
        String password
) {
    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .name(name)
                .password(password)
                .build();
    }
}
