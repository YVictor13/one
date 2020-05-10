package com.example.one.controller;

import com.example.one.Dto.QuestionDTO;
import com.example.one.Model.User;
import com.example.one.Service.QuestionService;
import com.example.one.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired(required =false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionService questionService;


    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        User user=null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        //        获取用户头像
        model.addAttribute("user",user);
        List<QuestionDTO> questionsList = questionService.List();
        model.addAttribute("questionList",questionsList);
        return "index";
    }
}
