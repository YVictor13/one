package com.example.one.Service;

import com.example.one.Dto.PaginationDTO;
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



    public PaginationDTO List(Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.Count();
        paginationDTO.setPagination(totalCount,page,size);
        //size(page-1)
        Integer offset = size*(paginationDTO.getCurrentPage()-1);
        List<Question> questions = questionMapper.List(offset,size);
        for (Question question : questions) {
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public  PaginationDTO profileList(Integer id, Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.profileCount(id);
        paginationDTO.setPagination(totalCount,page,size);

        //size(page-1)
        Integer offset = size*(paginationDTO.getCurrentPage()-1);
        List<Question> questions = questionMapper.profileList(id,offset,size);
        for (Question question : questions) {
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void creatOrUpdate(Question question) {

        if(question.getId()==null){
            //创建插入数据
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //数据已经存在，则更新数据
            question.setGmtModified(question.getGmtCreate());
            questionMapper.updatePassage(question);
        }
    }

}
