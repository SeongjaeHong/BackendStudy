package jpashop_re_group.jpashop_re.controller;

import jpashop_re_group.jpashop_re.domain.Member;
import jpashop_re_group.jpashop_re.domain.Product;
import jpashop_re_group.jpashop_re.service.MemberService;
import jpashop_re_group.jpashop_re.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("/order")
    public String orderForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Product> products = productService.findProducts();
        model.addAttribute("members", members);
        model.addAttribute("products");
        return "order/orderForm";
    }
}
