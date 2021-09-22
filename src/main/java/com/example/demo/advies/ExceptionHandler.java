package com.example.demo.advies;

import com.example.demo.exception.statusException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    //ExceptionHandler(Exception.class)处理所有的异常
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView handler(HttpServletRequest request, Throwable e, Model model){
        HttpStatus status = getStatus(request);
        if (e instanceof statusException){
            model.addAttribute("message",e.getMessage());
        }else{
            model.addAttribute("message","服务器出错稍后重试");
        }
        return new ModelAndView("error");
    }


    //获得异常状态码
    public HttpStatus getStatus(HttpServletRequest request){
        Integer status =(Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status ==null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(status);
    }

}
