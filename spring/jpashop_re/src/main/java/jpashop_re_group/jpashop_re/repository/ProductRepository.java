package jpashop_re_group.jpashop_re.repository;

import jpashop_re_group.jpashop_re.domain.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    @Transactional
    public void save(Product product) {
        if (product.getProductId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    public List<Product> findall() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public Product findbyId(Long productId) {
        return em.createQuery("SELECT p FROM Product p where p.productId = :productId", Product.class)
                .setParameter("productId", productId)
                .getSingleResult();

//        em.find(Product.class, productId);
    }
}
