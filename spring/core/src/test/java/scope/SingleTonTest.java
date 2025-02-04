package scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingleTonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("Find single bean1");
        SingletonBean bean1 = ac.getBean(SingletonBean.class);

        System.out.println("Find single bean2");
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("single bean1 = " + bean1);
        System.out.println("single bean2 = " + bean2);
        Assertions.assertThat(bean1).isSameAs(bean2);
        ac.close();
    }

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("Find proto bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);

        System.out.println("Find proto bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("proto bean1 = " + bean1);
        System.out.println("proto bean2 = " + bean2);
        Assertions.assertThat(bean1).isNotSameAs(bean2);

        bean1.destroy();
        bean2.destroy();
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean destroy");
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean destroy");
        }
    }
}
