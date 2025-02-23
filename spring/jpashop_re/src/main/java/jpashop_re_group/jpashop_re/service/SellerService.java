package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Seller;
import jpashop_re_group.jpashop_re.repository.MemberRepository;
import jpashop_re_group.jpashop_re.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Long save(Seller seller, Member member) {
        sellerRepository.save(seller);
        member.setSeller(seller);
        return seller.getSellerId();
    }

    public Seller findSeller(Long sellerId) {
        return sellerRepository.findById(sellerId);
    }

    public Member findregisteredMember(Long memberId) {
        return sellerRepository.findRegisteredMemberById(memberId);
    }
}
