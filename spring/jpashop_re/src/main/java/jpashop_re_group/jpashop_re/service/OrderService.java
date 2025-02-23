package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Orders;
import jpashop_re_group.jpashop_re.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Long saveOrder(Orders orders, Member member) {
        orders.setMember(member);
        orderRepository.saveOrder(orders);
        return orders.getOrderId();
    }

    public List<Orders> findOrdersByMemberId(Long memberId) {
        return orderRepository.findOrdersByMemberId(memberId);
    }
}
