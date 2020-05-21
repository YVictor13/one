package com.example.one.controller;

import com.example.one.Dto.QuestionDTO;
import com.example.one.Dto.ResultDTO;
import com.example.one.Service.QuestionService;
import com.example.one.exception.CustomizeErrorCode;
import com.example.one.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        //        获取用户头像
        QuestionDTO questionDTO = questionService.getById(id);
        //        增加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}

