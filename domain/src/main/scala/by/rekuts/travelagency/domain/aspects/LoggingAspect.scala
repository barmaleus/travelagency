package by.rekuts.travelagency.domain.aspects

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.{After, AfterReturning, Aspect}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
  @After("execution(* *.insert(..))")
  def log(point: JoinPoint): Unit = {
    LoggingAspect.log.info("!!!!!!!!!ASPECT-J-INSERT!!!!!!!!!!!" + point.getSignature.getName + " called...")
  }

  @AfterReturning(value = "@annotation(by.rekuts.travelagency.domain.aspects.LogReturn))", returning = "returnValue")
  def logResult(joinPoint: JoinPoint, returnValue: Any): Unit = {
    LoggingAspect.log.info("!!!!!!!!!!!!!!!!ASPECT-J-RETURN!!!!!!!!!!!!!!!!" + joinPoint.getSignature.getName + " called... " + returnValue)
  }

  object LoggingAspect {
    val log: Logger = LoggerFactory.getLogger(classOf[LoggingAspect])
  }
}
