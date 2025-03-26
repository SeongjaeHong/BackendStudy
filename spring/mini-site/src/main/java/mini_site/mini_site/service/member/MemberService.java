package mini_site.mini_site.service.member;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.domain.member.MemberLevel;
import mini_site.mini_site.exception.MemberException;
import mini_site.mini_site.repository.member.MemberRepository;
import mini_site.mini_site.service.member.dto.request.RegisterMemberRequest;
import mini_site.mini_site.service.member.dto.response.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberException("요청한 사용자를 찾을 수 없습니다."));
    }

    public Member findMemberById(Long memberId) {
        return getMemberById(memberId);
    }

    @Transactional
    public MemberResponse registerMember(RegisterMemberRequest registerMemberRequest) {
        Member member = registerMemberRequest.toEntity();
        memberRepository.save(member);
        return new MemberResponse(member.getId(), member.getName(), member.getMemberLevel());
    }

    @Transactional
    public MemberResponse grantMember(Long memberId) {
        Member member = getMemberById(memberId);
        member.setMemberLevel(MemberLevel.ADMIN);

        return new MemberResponse(member.getId(), member.getName(), member.getMemberLevel());
    }
}
