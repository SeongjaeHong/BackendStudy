package hello_group.hello_spring.service;

import hello_group.hello_spring.domain.Member;
import static org.assertj.core.api.Assertions.*;

import hello_group.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MemberServiceTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    @AfterEach
    void clearMemberRepository() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // Given
        Member member = new Member();
        member.setName("spring");

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void duplicatedMemberException() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("Member already exists.");

        /*
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Member already exists.");
        }
        */

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}