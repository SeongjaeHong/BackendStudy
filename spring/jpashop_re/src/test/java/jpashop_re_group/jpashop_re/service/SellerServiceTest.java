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
    @Rollback(value = false)
    public void 판매자등록() throws Exception {
        Member member = new Member();
        Member member2 = new Member();
        member.setMemberName("member-1");
        member2.setMemberName("member-2");
        memberService.save(member);
        memberService.save(member2);

        Seller seller = new Seller();
        Long sellerId = sellerService.save(seller, member2);
        Seller savedSeller = sellerService.findSeller(sellerId);

        assertEquals(seller, savedSeller);
    }
}