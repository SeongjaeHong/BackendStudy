package spring_basic.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring_basic.core.annotation.MainDiscountPolicy;
import spring_basic.core.member.Grade;
import spring_basic.core.member.Member;

@Component
@Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountRate / 100;
        } else {
            return 0;
        }
    }
}
