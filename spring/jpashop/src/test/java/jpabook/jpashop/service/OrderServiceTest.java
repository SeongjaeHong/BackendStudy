package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = createMember();

        Book book = createBook(10000, 10, "book1");

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문 상품 종류의 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 상품 가격이 정확해야 한다.");
        assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = createMember();
        Book book = createBook(10000, 10, "book1");

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCELED, getOrder.getStatus(), "STATUS는 CANCELED여야 한다.");
        assertEquals(10, book.getStockQuantity(), "주문 취소시 재고가 복구되어야 한다.");
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        Member member = createMember();
        Book book = createBook(10000, 10, "book1");

        int orderCount = 22;

        assertThrowsExactly(NotEnoughStockException.class,
                () -> {
                    orderService.order(member.getId(), book.getId(), orderCount);
                },
                "NotEnoughStockException가 발생해야합니다."
        );
    }

    private Book createBook(int price, int stockQuantity, String book1) {
        Book book = new Book();
        book.setName(book1);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("Member1");
        member.setAddress(new Address("Seoul", "Street", "1234"));
        em.persist(member);
        return member;
    }
}