package mini_site.mini_site.service.member.dto.request;

import mini_site.mini_site.domain.member.Member;

public record RegisterMemberRequest(
        String name,
        String password
) {
    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .build();
    }
}
