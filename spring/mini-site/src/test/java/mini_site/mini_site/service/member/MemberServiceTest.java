package mini_site.mini_site.service.member;

import mini_site.mini_site.domain.member.Member;
import mini_site.mini_site.domain.member.MemberLevel;
import mini_site.mini_site.repository.member.MemberRepository;
import mini_site.mini_site.service.member.dto.request.RegisterMemberRequest;
import mini_site.mini_site.service.member.dto.response.MemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("사용자 ID를 이용해 사용자를 조회한다.")
    @Test
    void findMemberById() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name1", "pass");
        MemberResponse memberResponse = memberService.registerMember(registerMemberRequest);

        // when
        MemberResponse foundMember = memberService.findMemberById(memberResponse.memberId());

        // then
        assertAll(
                () -> Assertions.assertThat(foundMember.name()).isEqualTo(memberResponse.name()),
                () -> Assertions.assertThat(foundMember.memberLevel()).isEqualTo(memberResponse.memberLevel())
        );
    }

    @DisplayName("사용자를 등록한다.")
    @Test
    void registerMember() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name1", "pass");
        Member member = memberRepository.save(new Member("name1", "pass"));

        // when
        MemberResponse memberResponse = memberService.registerMember(registerMemberRequest);
        Member foundMember = memberRepository.findById(member.getId()).get();

        // then
        assertAll(
                // Check name
                () -> Assertions.assertThat(foundMember.getName()).isEqualTo(member.getName()),
                () -> Assertions.assertThat(foundMember.getName()).isEqualTo(memberResponse.name()),

                // Check default memberLevel
                () -> Assertions.assertThat(foundMember.getMemberLevel()).isEqualTo(memberResponse.memberLevel())
        );
    }

    @DisplayName("사용자에게 관리자 권한을 부여한다.")
    @Rollback(value = false)
    @Test
    void grantAdmin() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name1", "pass");
        MemberResponse memberResponse = memberService.registerMember(registerMemberRequest);

        // when
        memberService.grantMember(memberResponse.memberId());
        MemberResponse foundMember = memberService.findMemberById(memberResponse.memberId());

        // then
        assertEquals(MemberLevel.ADMIN, foundMember.memberLevel());
    }
}