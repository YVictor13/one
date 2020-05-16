package com.example.one.controller;

import com.example.one.Dto.QuestionDTO;
import com.example.one.Model.Question;
import com.example.one.Service.QuestionService;
import com.example.one.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QUestionController {
    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model
    ){
        QuestionDTO questionDTO = questionService.geiById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }


}
