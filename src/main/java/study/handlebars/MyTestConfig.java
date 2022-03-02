package study.handlebars;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import study.handlebars.aop.TimeTraceAop;

@Component
public class MyTestConfig {

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}