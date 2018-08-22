package by.rekuts.travelagency.domain.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @After("execution(* *.insert(..))")
    public void log(JoinPoint point) {
        log.info("!!!!!!!!!ASPECT-J-INSERT!!!!!!!!!!!" + point.getSignature().getName() + " called...");
    }

    @AfterReturning(value = "@annotation(by.rekuts.travelagency.domain.aspects.LogReturn))", returning = "returnValue")
    public void logResult(JoinPoint joinPoint, Object returnValue) {
        log.info("!!!!!!!!!!!!!!!!ASPECT-J-RETURN!!!!!!!!!!!!!!!!" + joinPoint.getSignature().getName() +
                " called... " + returnValue);
    }
}
