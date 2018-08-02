package by.rekuts.travelagency.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @After("execution(* *.insert(..))")
    public void log(JoinPoint point) {
        log.info("!!!!!!!!!ASPECT-J-INSERT!!!!!!!!!!!" + point.getSignature().getName() + " called...");
    }

    @AfterReturning(value = "@annotation(LogReturn))", returning = "returnValue")
    public void logResult(JoinPoint joinPoint, Object returnValue) {
        Signature signature =  joinPoint.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();
        if (!returnType.equals("void")) {
            log.info("!!!!!!!!!!!!!!!!ASPECT-J-RETURN!!!!!!!!!!!!!!!!" + joinPoint.getSignature().getName() +
                    " called... " + returnValue);
        }
    }
}
