package study.handlebars.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import study.handlebars.dto.post.QPostRequestDto;
import study.handlebars.model.Member;
import study.handlebars.model.Post;
import study.handlebars.repository.MemberRepository;
import study.handlebars.repository.MemberRepositoryCustom;
import study.handlebars.repository.MemberRepositoryImpl;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static study.handlebars.model.QMember.member;
import static study.handlebars.model.QPost.post;


@SpringBootTest
class MemberSeriveTest {

/*
*   git checkout -b deploy-wms
*   git add 작업파일명
*  git commit -m "change wms-billing prod deploy"
*  git push origin deploy-wms
* */

    @Test
    public void 테스트2(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(testBin.class);
    }

    @Configuration
    static class testBin{
        static private MemberRepository memberRepository;

        @Autowired(required = false)
        public testBin(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Bean
        static public JPAQueryFactory jpaQueryFactory(EntityManager em){
            return new JPAQueryFactory(em);
        }

        @Bean
        static public PasswordEncoder passwordEncoder(){
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
    }
}