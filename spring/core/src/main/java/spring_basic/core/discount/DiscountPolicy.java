package spring_basic.core.discount;

import spring_basic.core.member.Member;

public interface DiscountPolicy {

    /**
     * @return Discount price
     */
    int discount(Member member, int price);

}
