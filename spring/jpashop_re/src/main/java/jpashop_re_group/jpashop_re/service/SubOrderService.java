package jpashop_re_group.jpashop_re.service;

import jpashop_re_group.jpashop_re.domain.Suborder;
import jpashop_re_group.jpashop_re.repository.SubOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubOrderService {

    private final SubOrderRepository subOrderRepository;

    public void saveSubOrder(Suborder suborder) {
        subOrderRepository.saveSubOrder(suborder);
    }

    public List<Suborder> findSubOrdersByOrdersId(Long ordersId) {
        return subOrderRepository.findSubOrdersByOrdersId(ordersId);
    }

}
