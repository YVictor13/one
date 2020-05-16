package com.example.one.controller;

import com.example.one.Dto.QuestionDTO;
import com.example.one.Model.Question;
import com.example.one.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QUestionController {
    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Integer id){
        QuestionDTO questionDTO = questionMapper.geiById(id);
        return "question";
    }


}
