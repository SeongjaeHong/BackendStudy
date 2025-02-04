package spring_basic.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic.core.member.Grade;
import spring_basic.core.member.Member;
import spring_basic.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "member1", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println(findMember);
    }
}
