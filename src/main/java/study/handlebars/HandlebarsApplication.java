package study.handlebars;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.handlebars.aop.TimeTraceAop;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing //JPA Auditing 활성화
public class HandlebarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandlebarsApplication.class, args);
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em){
		System.out.println("em = " + em);
		return new JPAQueryFactory(em);
	}

/*	@Bean
	public TimeTraceAop timeTraceAop(){
		return new TimeTraceAop();
	}*/
}
