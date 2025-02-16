package jpashop_re_group.jpashop_re.repository;

import jpashop_re_group.jpashop_re.domain.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    @Transactional
    public void save(Product product) {
        em.persist(product);
    }
}
