package spring_basic.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic.core.discount.DiscountPolicy;
import spring_basic.core.discount.RateDiscountPolicy;
import spring_basic.core.member.MemberRepository;
import spring_basic.core.member.MemberService;
import spring_basic.core.member.MemberServiceImpl;
import spring_basic.core.member.MemoryMemberRepository;
import spring_basic.core.order.OrderService;
import spring_basic.core.order.OrderServiceImpl;

//@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(MemberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(MemberRepository(), DiscountPolicy());
    }

    @Bean
    public static MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public static DiscountPolicy DiscountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
