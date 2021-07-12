package com.anteash.rng.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class CustomErrorController implements ErrorController {
    @Value("${page.error}")
    private String ERROR_JSP;

    @GetMapping(path = "/error")
    public ModelAndView error(HttpServletRequest httpRequest, Model model) {
        ModelAndView errorPage = new ModelAndView(ERROR_JSP);
        model.addAttribute("timestamp", new Date(httpRequest.getSession().getLastAccessedTime()));
        model.addAttribute("status", getErrorCode(httpRequest));
        model.addAttribute("message", httpRequest.getAttribute("javax.servlet.error.message"));
        model.addAttribute("error", httpRequest.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR"));
        model.addAttribute("exception", httpRequest.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION"));
        model.addAttribute("trace", httpRequest.getAttribute("javax.servlet.error.exception"));
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
