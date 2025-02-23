package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Product;
import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Seller;
import jpashop_re_group.jpashop_re.exception.InvalidUserException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import jpashop_re_group.jpashop_re.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProductServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired ProductService productService;
    @Autowired SellerService sellerService;

    Member member = new Member();
    Seller seller = new Seller();
    Product product = new Product();

    void presetSeller() {
        member.setMemberName("User-A");
        memberRepository.save(member);
        sellerService.save(seller, member);
    }

    void presetProduct() {
        product.setPrice(1000);
        product.setQuantity(5);
        productService.save(product);
    }

    @Test
    public void 가격변경() throws Exception {
        presetSeller();
        presetProduct();
        productService.save(product);
        product.setSeller(seller);

        // 적법한 판매자가 가격변경 시도
        Boolean result =  product.changePrice(2000, seller.getSellerId());
        assertEquals(true, result);
        assertEquals(2000, product.getPrice());

        // 적법하지 않은 판매자가 가격변경 시도
        InvalidUserException exception = assertThrows(
                InvalidUserException.class,
                () -> {
                    product.changePrice(2000, -1L);
                },
                "InvalidUserException이 발생해야 한다."
        );
    }
}