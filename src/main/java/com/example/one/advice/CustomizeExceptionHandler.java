package com.example.one.advice;

import com.example.one.exception.CustomizeException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)

   ModelAndView handler( Throwable e, Model model){
        if(e instanceof CustomizeException){
             model.addAttribute("Errormessage",e.getMessage());
        }else{
             model.addAttribute("Errormessage","页面加载异常！！！");
        }
      return new ModelAndView("error");
    }

}
