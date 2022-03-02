package study.handlebars;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//DI 컨테이너 ( 의존관계 주입 )
//config.. - 스프링에서 사용할 수 있는 설정 정보를 주입하기 위해 사용. 디폴트로 싱글톤으로 관리된다.
//외부 라이브러리 또는 내장 클래스를 빈으로 등록하는 경우.
@Configuration
public class BeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}