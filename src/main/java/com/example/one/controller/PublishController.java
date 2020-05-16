package com.example.one.controller;

import com.example.one.Dto.QuestionDTO;
import com.example.one.Model.Question;
import com.example.one.Model.User;
import com.example.one.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
//    @Autowired(required = false)
//    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tag,
            HttpServletRequest request,
            @RequestParam(value = "id",required = false) Integer id,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        User user =(User)request.getSession().getAttribute("user");

        if (user!=null){
            model.addAttribute("user",user);
        }


        if(user==null){
            model.addAttribute("error","*用户未登陆");
            return "publish";
        }
        if(title==null|| title.equals("")){
            model.addAttribute("error","问题标题不能为空！！！");
            return "publish";
        }
        if(description==null|| description.equals("")){
            model.addAttribute("error","问题描述不能为空！！！");
            return "publish";
        }
        if(tag==null|| tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.creatOrUpdate(question);
//        questionMapper.create(question);

//        返回首页
            return "redirect:/";
    }
}
