package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Orders;
import jpashop_re_group.jpashop_re.domain.Product;
import jpashop_re_group.jpashop_re.domain.Suborder;
import jpashop_re_group.jpashop_re.domain.item.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrdersServiceTest {

    @Autowired MemberService memberService;
    @Autowired OrderService orderService;
    @Autowired ProductService productService;

    Member setMember(String name) {
        Member member = new Member();
        member.setMemberName(name);
        memberService.save(member);
        return member;
    }

    @Test
//    @Rollback(value = false)
    public void 주문조회() {
        Member member = setMember("member-A");

        Orders order = new Orders();
        Orders order2 = new Orders();
        orderService.saveOrder(order, member);
        orderService.saveOrder(order2, member);

        List<Orders> orders = orderService.findOrdersByMemberId(member.getMemberId());
        assertEquals(order, orders.getFirst());
        assertEquals(order2, orders.getLast());
    }

    @Test
    public void 주문하기() throws Exception {
        Member member = setMember("member-A");
        Orders order = new Orders();

        Book book = new Book();
        book.setName("Book-A");
        book.setPrice(10000L);
        book.setQuantity(500);
        productService.save(book);

        Book book2 = new Book();
        book2.setName("Book-B");
        book2.setPrice(7000L);
        book2.setQuantity(200);
        productService.save(book2);

        Suborder suborder1 = new Suborder();
        suborder1.setOrders(order);
        suborder1.setProduct(book);
        suborder1.setQuantity(10);
        order.addSubOrder(suborder1);

        Suborder suborder2 = new Suborder();
        suborder2.setOrders(order);
        suborder2.setProduct(book2);
        suborder2.setQuantity(6);
        order.addSubOrder(suborder2);

        orderService.saveOrder(order, member);

        Long aggregate = order.getAggregate();
        List<Orders> orders = orderService.findOrdersByMemberId(member.getMemberId());
        assertEquals(aggregate, orders.getFirst().getAggregate());
        assertEquals(500-10, book.getQuantity());
        assertEquals(200-6, book2.getQuantity());
    }
}