package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Orders;
import jpashop_re_group.jpashop_re.domain.Suborder;
import jpashop_re_group.jpashop_re.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final SubOrderService subOrderService;

    public Long saveOrder(Orders orders, Member member) {
        orders.setMember(member);
        orders.setAggregate();
        orderRepository.saveOrder(orders);
        return orders.getOrderId();
    }

    public Long aggregate(Orders orders) {
        List<Suborder> suborders = findSubOrdersByOrdersId(orders.getOrderId());

        Long price = 0L;
        for (Suborder suborder: suborders) {
            price += suborder.getProduct().getPrice();
        }
        return price;
    }

    public List<Orders> findOrdersByMemberId(Long memberId) {
        return orderRepository.findOrdersByMemberId(memberId);
    }
    public List<Suborder> findSubOrdersByOrdersId(Long ordersId) {
        return subOrderService.findSubOrdersByOrdersId(ordersId);
    }
}
