package jpashop_re_group.jpashop_re.repository;

import jakarta.persistence.EntityManager;
import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellerRepository {

    private final EntityManager em;

    @Transactional
    public void save(Seller seller) {
        em.persist(seller);
    }

    public Seller findById(Long sellerId) {
        return em.createQuery("select s from Seller s where s.sellerId = :sellerId", Seller.class)
                .setParameter("sellerId", sellerId)
                .getSingleResult();
    }

    public Member findRegisteredMemberById(Long memberId) {
        return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }
}
