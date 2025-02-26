package jpashop_re_group.jpashop_re.repository;

import jakarta.persistence.EntityManager;
import jpashop_re_group.jpashop_re.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderRepository {

    private final EntityManager em;

    @Transactional
    public void saveOrder(Orders orders) {
        em.persist(orders);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        jakarta.persistence.Query query = em.createQuery("DELETE FROM Orders WHERE orderId = :orderId");
        query.setParameter("orderId", orderId);

        query.executeUpdate();
    }

    public List<Orders> findOrdersByMemberId(Long memberId) {
        // "select o from Orders o where o.member.memberId = :memberId"
        return em.createQuery("SELECT o FROM Orders o JOIN o.member m WHERE m.memberId = :memberId", Orders.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
