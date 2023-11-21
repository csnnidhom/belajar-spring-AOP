package belajar.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("target(belajar.springaop.service.HelloService)")
    public void HelloServiceMethod(){

    }

    @Before("HelloServiceMethod()")
    public void beforeHelloServiceMethod2(){
        log.info("Before HelloService Method Again");
    }

    @Around("HelloServiceMethod()")
    public Object aroundHelloServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        try{
            log.info("Around Before " + className + "." + methodName + "()");
            return joinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable){
            log.info("Around Error " + className + "." + methodName + "()");
            return throwable;
        }finally {
            log.info("Around Finnaly " + className + "." + methodName + "()");
        }
    }

    @Pointcut("execution(* belajar.springaop.service.HelloService.*(java.lang.String))")
    public void pointcutHelloServiceStringParam(){

    }

//    @Before("pointcutHelloServiceStringParam()")
//    public void logStringParameter(JoinPoint joinPoint){
//        Object value = joinPoint.getArgs()[0];
//        log.info("Execute method with parameter : " + value);
//    }

    @Before("pointcutHelloServiceStringParam() && args(name)")
    public void logStringParameter(String name){
        log.info("Execute method with parameter : " + name);
    }

    @Pointcut("execution(* belajar.springaop.service.*.*(..))")
    public void pointcutServicePackage(){

    }

    @Pointcut("bean(*Service)")
    public void pointcutServiceBean(){

    }

    @Pointcut("execution(public * *(..))")
    public void pointcutPublicMethod(){

    }

    @Pointcut("pointcutServicePackage() && pointcutServiceBean() && pointcutPublicMethod()")
    public void publicMethodForService(){

    }

    @Before("publicMethodForService()")
    public void logAllPublicServiceMethod(){
        log.info("Log For All public service method");
    }

}
