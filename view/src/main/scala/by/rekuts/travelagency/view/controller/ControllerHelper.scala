package by.rekuts.travelagency.view.controller

import org.springframework.web.context.request.{RequestContextHolder, ServletRequestAttributes}

class ControllerHelper {
  private[controller] def getSessionUserId = {
    val attr = RequestContextHolder.currentRequestAttributes.asInstanceOf[ServletRequestAttributes]
    val session = attr.getRequest.getSession
    session.getAttribute("sesUserId").asInstanceOf[Int]
  }
}
