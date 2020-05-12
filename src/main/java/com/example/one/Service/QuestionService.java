package com.example.one.Service;

import com.example.one.Dto.QuestionDTO;
import com.example.one.Model.Question;
import com.example.one.Model.User;
import com.example.one.mapper.QuestionMapper;
import com.example.one.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    public List<QuestionDTO> List(Integer page, Integer size) {
        //size(page-1)
        Integer offset = size*(page-1);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = questionMapper.List(offset,size);
        for (Question question : questions) {
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        return questionDTOList;
    }
}
