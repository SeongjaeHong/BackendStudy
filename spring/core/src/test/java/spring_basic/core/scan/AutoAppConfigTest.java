package spring_basic.core.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic.core.AutoAppConfig;
import spring_basic.core.member.MemberService;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService ms = ac.getBean(MemberService.class);
        Assertions.assertThat(ms).isInstanceOf(MemberService.class);
    }
}
