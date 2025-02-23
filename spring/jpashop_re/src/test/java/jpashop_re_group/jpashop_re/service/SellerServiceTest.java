package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Seller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SellerServiceTest {

    @Autowired SellerService sellerService;
    @Autowired MemberService memberService;

    @Test
    public void 판매자등록() {
        Member member = new Member();
        member.setMemberName("member-1");
        memberService.save(member);

        Seller seller = new Seller();
        Long sellerId = sellerService.save(seller, member);
        Seller savedSeller = sellerService.findSeller(sellerId);

        assertEquals(seller, savedSeller);
    }

    @Test
    public void 판매자로등록된멤버찾기() {
        Member member = new Member();
        member.setMemberName("member-1");
        memberService.save(member);

        Seller seller = new Seller();
        sellerService.save(seller, member);
        Member searchedMember = sellerService.findregisteredMember(member.getMemberId());

        assertEquals(member, searchedMember);
    }
}