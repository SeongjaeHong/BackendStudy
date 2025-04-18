package mini_site.mini_site.service.member;

import lombok.RequiredArgsConstructor;
import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.domain.member.MemberLevel;
import mini_site.mini_site.exception.MemberException;
import mini_site.mini_site.exception.MemberException.MemberExceptionMessage;
import mini_site.mini_site.repository.member.MemberRepository;
import mini_site.mini_site.service.member.dto.request.RegisterMemberRequest;
import mini_site.mini_site.service.member.dto.response.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionMessage.MEMBER_ID_NOT_FOUND, memberId));
    }

    public Optional<Member> findMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public Optional<Member> findMemberByName(String memberName) {
        return memberRepository.findByName(memberName);
    }

    @Transactional
    public MemberResponse registerMember(RegisterMemberRequest registerMemberRequest) {
        Member member = registerMemberRequest.toEntity();
        memberRepository.save(member);
        return new MemberResponse(member.getId(), member.getName(), member.getMemberLevel());
    }

    @Transactional
    public MemberResponse grantMember(Long memberId) {
        Member member = findMemberById(memberId);
        member.setMemberLevel(MemberLevel.ADMIN);

        return new MemberResponse(member.getId(), member.getName(), member.getMemberLevel());
    }
}
