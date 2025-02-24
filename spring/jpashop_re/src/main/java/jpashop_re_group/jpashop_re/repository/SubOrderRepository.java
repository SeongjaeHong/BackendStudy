package jpashop_re_group.jpashop_re.repository;

import jakarta.persistence.EntityManager;
import jpashop_re_group.jpashop_re.domain.Suborder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubOrderRepository {

    private final EntityManager em;

    @Transactional
    public void saveSubOrder(Suborder suborder) {
        em.persist(suborder);
    }

    public List<Suborder> findSubOrdersByOrdersId(Long ordersId) {
        return em.createQuery("SELECT subo FROM Suborder JOIN subo.orders o " +
                "WHERE o.orderId == :ordersId", Suborder.class)
                .setParameter("ordersId", ordersId)
                .getResultList();
    }
}
