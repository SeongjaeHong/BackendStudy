package hello_group.hello_spring.service;

import hello_group.hello_spring.domain.Member;
import hello_group.hello_spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateMemberDuplication(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateMemberDuplication(Member member) {
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("Member already exists.");
        });
        */
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("Member already exists.");
                        });
    }

    public List<Member> findMembers() {
        return memberRepository.findall();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
