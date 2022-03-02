package study.handlebars;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.handlebars.service.MemberSerive;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberSerive memberSerive;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/assets/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberSerive).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/signup", "/signin").permitAll()
                .anyRequest().authenticated();

        http.cors().disable()      // cors 비활성화
                .csrf().disable();      // csrf 비활성화

        http.formLogin()
                .loginPage("/signin")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll();

        http.logout()
                .logoutUrl("/signout")       // logout 처리할 페이지 설정
                .logoutSuccessUrl("/")      // logout 성공 시 redirect 페이지 설정
                //.addLogoutHandler()         // logout 시 부가적인 작업 설정
                //.logoutSuccessHandler()    // logout 성공 시 부가적인 작업 설정
                .invalidateHttpSession(true)// logout 시 HttpSession invalidated
                .deleteCookies();      // Cookie 기반 인증 사용 시 logout 할 때      Cookie 삭제
                //.logoutRequestMatcher();

        http.headers().frameOptions().disable();
    }
}
