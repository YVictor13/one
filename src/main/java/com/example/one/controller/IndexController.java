package com.example.one.controller;

import com.example.one.Dto.PaginationDTO;
import com.example.one.Dto.ResultDTO;
import com.example.one.Service.QuestionService;
import com.example.one.exception.CustomizeErrorCode;
import com.example.one.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {
    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "10")Integer size) {
        //        获取用户头像
        PaginationDTO Pagination = questionService.List(page,size);
        model.addAttribute("Pagination",Pagination);
        return "index";
    }
}
