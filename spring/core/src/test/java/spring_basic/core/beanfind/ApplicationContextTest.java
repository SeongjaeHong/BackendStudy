package spring_basic.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic.core.AppConfig;
import spring_basic.core.member.MemberRepository;
import spring_basic.core.member.MemberService;
import spring_basic.core.member.MemberServiceImpl;
import spring_basic.core.member.MemoryMemberRepository;

import java.util.Map;

public class ApplicationContextTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Show all beans")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String name: beanDefinitionNames) {
            Object bean = ac.getBean(name);
            System.out.println("name = " + name + " object = " + bean);
        }
    }

    @Test
    @DisplayName("Show all application beans")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String name: beanDefinitionNames) {
            BeanDefinition bean = ac.getBeanDefinition(name);

            if (bean.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object beanO = ac.getBean(name);
                System.out.println("name = " + name + " object = " + beanO);
            }
        }
    }

    @Test
    @DisplayName("Search by bean names")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Search by bean types")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Search by bean names failed")
    void findBeanByNameX() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("Any-random-name", MemberService.class));
    }

    @Test
    @DisplayName("Duplication error occurred when more than two are searched")
    void findBeanByTypeDuplicate() {
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(SameBeanConfig.class);
        org.junit.jupiter.api.Assertions.assertThrows(
                NoUniqueBeanDefinitionException.class,
                () -> ac2.getBean(MemberRepository.class)
        );
    }

    @Test
    @DisplayName("When there are more than two bean of a same type, you can name the bean")
    void findBeanByTypeDuplicate2() {
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(SameBeanConfig.class);
        MemberRepository memberRepository = ac2.getBean("mr1", MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("Search all beans of a same type")
    void findAllBeanByType() {
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(SameBeanConfig.class);
        Map<String, MemberRepository> beansOfType = ac2.getBeansOfType(MemberRepository.class);
        for (String key: beansOfType.keySet()) {
            System.out.println("Key = " + key + " value = " + beansOfType.get(key));
        }
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository mr1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository mr2() {
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("Spring container and singleton")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService ms1 = ac.getBean(MemberService.class);
        MemberService ms2 = ac.getBean(MemberService.class);

        System.out.println("ms1: " + ms1);
        System.out.println("ms2: " + ms2);
        Assertions.assertThat(ms2).isSameAs(ms1);
    }

}
