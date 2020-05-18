package com.example.one.controller;

import com.example.one.Dto.CommentDTO;
import com.example.one.mapper.CommentMapper;
import com.example.one.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired(required = false)
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment =new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1L);
        comment.setCommentCount(0L);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        Map<Object,Object> objectObjectMap =new HashMap<>();
        objectObjectMap.put("message","成功");
        return objectObjectMap;
    }
}
