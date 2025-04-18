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
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("id", "name", "pass");
        MemberResponse memberResponse = memberService.registerMember(registerMemberRequest);

        // when
        Member foundMember = memberService.findMemberById(memberResponse.memberId());

        // then
        assertAll(
                () -> Assertions.assertThat(foundMember.getName()).isEqualTo(memberResponse.name()),
                () -> Assertions.assertThat(foundMember.getMemberLevel()).isEqualTo(memberResponse.memberLevel())
        );
    }

    @DisplayName("사용자를 등록한다.")
    @Test
    void registerMember() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("id", "name123", "pass");

        // when
        MemberResponse memberResponse = memberService.registerMember(registerMemberRequest);
        Member foundMember = memberRepository.findById(memberResponse.memberId()).get();

        // then
        assertAll(
                () -> Assertions.assertThat(foundMember.getName()).isEqualTo(memberResponse.name()),
                () -> Assertions.assertThat(foundMember.getMemberLevel()).isEqualTo(memberResponse.memberLevel())
        );
    }

    @DisplayName("사용자에게 관리자 권한을 부여한다.")
    @Test
    void grantAdmin() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("id", "admin", "pass");
        MemberResponse memberResponse = memberService.registerMember(registerMemberRequest);

        // when
        memberService.grantMember(memberResponse.memberId());
        Member foundMember = memberService.findMemberById(memberResponse.memberId());

        // then
        assertEquals(MemberLevel.ADMIN, foundMember.getMemberLevel());
    }
}