package study.handlebars.beandef;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import study.handlebars.BeanConfig;
import study.handlebars.MyTestConfig;

@WebAppConfiguration
@SpringBootTest
public class BeanDefTest {
    @Autowired
    private DefaultListableBeanFactory dfBean;

    @Test
    void 테스트(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(BeanConfig.class);
        String[] beanNamesForAnnotation = ac.getBeanDefinitionNames();

        for (String s : beanNamesForAnnotation) {
            System.out.println("my Bean = " + s);
        }
    }

    @Test
    void 테스트2(){
        String[] beanDefinitionNames = dfBean.getBeanDefinitionNames();
        for(String name : beanDefinitionNames){
            System.out.println("name = " + name);
        }
    }
    @Test
    void 테스트3(){
        MyTestConfig myTestConfig = dfBean.getBean("myTestConfig", MyTestConfig.class);
        System.out.println("myTestConfig = " + myTestConfig);
    }
}

/*
* Configration 은 bean 등록시 의존관계를 수동을 주입할수있고, 한눈에 보기 좋기 들어옴
* Configration, @ComponentScan 으로 자동 주입의 옵션들을 사용할 수 있고(자동주입은 Component로 Autowired로 구분된다)
*  @SpringBootApplication..  에는 @ComponentScan가 자동으로 포함되어있음.
* */