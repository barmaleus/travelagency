package by.rekuts.travelagency.view.controller

import javax.servlet.http.HttpServletRequest
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseStatus}
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class GlobalDefaultExceptionHandler {
  @ExceptionHandler(value = Array(classOf[Exception]))
  @throws[Exception]
  def defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView = {
    if (AnnotationUtils.findAnnotation(e.getClass, classOf[ResponseStatus]) != null) throw e
    val mav = new ModelAndView
    mav.addObject("name", e.getClass.getName)
    mav.addObject("message", e.getLocalizedMessage)
    mav.addObject("stackTrace", e.getStackTrace)
    mav.addObject("url", req.getRequestURL)
    mav.setViewName("error")
    mav
  }
}
