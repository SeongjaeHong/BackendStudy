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

    public void cancelOrder(Orders orders, Member member) throws IllegalAccessException{
        for (Suborder so: findSubOrdersByOrdersId(orders.getOrderId())) {
            subOrderService.cancelSuborder(so);
        }
        if (orders.getMember().equals(member)) {
            orderRepository.deleteOrder(orders.getOrderId());
        } else {
            throw new IllegalAccessException();
        }
    }

    public void cancelSuborder(Suborder suborder, Member member) throws IllegalAccessException{
        if (suborder.getOrders().getMember().equals(member)) {
            subOrderService.cancelSuborder(suborder);
        } else {
            throw new IllegalAccessException();
        }
    }

    public List<Orders> findOrdersByMemberId(Long memberId) {
        return orderRepository.findOrdersByMemberId(memberId);
    }
    public List<Suborder> findSubOrdersByOrdersId(Long ordersId) {
        return subOrderService.findSubOrdersByOrdersId(ordersId);
    }
}
