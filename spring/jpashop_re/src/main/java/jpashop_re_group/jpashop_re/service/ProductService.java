package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jpashop_re_group.jpashop_re.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> findProducts() {
        return productRepository.findall();
    }
}
