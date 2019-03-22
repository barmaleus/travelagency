package by.rekuts.travelagency.view.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

class ControllerHelper {
    int getSessionUserId() {
        var attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        return (int) session.getAttribute("sesUserId");
    }
}
