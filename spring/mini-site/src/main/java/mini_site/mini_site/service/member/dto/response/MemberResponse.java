package mini_site.mini_site.service.member.dto.response;

import mini_site.mini_site.domain.member.MemberLevel;

public record MemberResponse(
        Long memberId,
        String name,
        MemberLevel memberLevel
) {
}
