package hello_group.hello_spring.service;

import hello_group.hello_spring.domain.Member;
import hello_group.hello_spring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void signUp() {
        Member member = new Member();
        member.setName("spring");

        Long saveId = memberService.join(member);

        Member searchedMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(searchedMember.getName());
    }

    @Test
    public void duplicatedMemberException() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("Member already exists.");
    }
}
