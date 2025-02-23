package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(Member member) {
        memberRepository.save(member);
        return member.getMemberId();
    }
}
