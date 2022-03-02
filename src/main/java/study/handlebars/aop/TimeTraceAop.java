package study.handlebars.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

    @Around("execution(* study.handlebars.service..*(..))")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        }finally {
            long timeMs = System.currentTimeMillis() - start;
            System.out.println("JoinPoint : " + proceedingJoinPoint.toString() + ", " + timeMs + "ms");
        }
    }
}
