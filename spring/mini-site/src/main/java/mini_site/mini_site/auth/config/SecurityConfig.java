package mini_site.mini_site.auth.config;

import mini_site.mini_site.auth.MemberLoginFailureHandler;
import mini_site.mini_site.auth.MemberLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // disable default spring security chain and make this as primary
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)  // disable csrf filtration
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/signup", "/css/**", "/assets/**").permitAll()  // allow access to login page and static files
                        .anyRequest().authenticated()  // all other requests need auth
                )
                .formLogin(form -> form
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .loginPage("/login")  // GET: login page URL
                        .loginProcessingUrl("/login")  // POST: login process URL
                        .successHandler(new MemberLoginSuccessHandler())
                        .failureHandler(new MemberLoginFailureHandler()))
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
