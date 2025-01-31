package hello_group.hello_spring;

import hello_group.hello_spring.repository.MemoryMemberRepository;
//import hello_group.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memoryMemberRepository());
//    }

    @Bean
    public MemoryMemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }
}
