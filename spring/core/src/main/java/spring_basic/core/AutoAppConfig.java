package spring_basic.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(  // Scan all @Component
        basePackages = "spring_basic.core",  // 하위 패키지만 검색. 지정하지 않으면 @ComponentScan이 있는 패키지 내 모두 검색
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class  // AppConfig 내부의 @Configuration class 제외
        )
)
public class AutoAppConfig {
}
