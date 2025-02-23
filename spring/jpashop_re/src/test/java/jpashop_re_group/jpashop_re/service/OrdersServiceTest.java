package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Orders;
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

    @Test
    @Rollback(value = false)
    public void 주문조회() throws Exception {
        Member member = new Member();
        member.setMemberName("member-A");
        memberService.save(member);

        Orders order = new Orders();
        Orders order2 = new Orders();
        orderService.saveOrder(order, member);
        orderService.saveOrder(order2, member);

        List<Orders> orders = orderService.findOrdersByMemberId(member.getMemberId());
        assertEquals(order, orders.getFirst());
        assertEquals(order2, orders.getLast());
    }
}