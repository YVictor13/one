package com.example.one.Service;

import com.example.one.Dto.PaginationDTO;
import com.example.one.Dto.QuestionDTO;
import com.example.one.model.Question;
import com.example.one.model.QuestionExample;
import com.example.one.model.User;
import com.example.one.mapper.QuestionMapper;
import com.example.one.mapper.UserMapper;
import com.example.one.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPagination(totalCount,page,size);
        //size(page-1)
        Integer offset = size*(paginationDTO.getCurrentPage()-1);
//        List<Question> questions = questionMapper.selectByExample(offset,size);

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),
                new RowBounds(offset, size));
        for (Question question : questions) {


            User user= userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public  PaginationDTO profileList(Long id, Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount = (int)questionMapper.countByExample(example);


//        Integer totalCount = questionMapper.profileCount(id);

        paginationDTO.setPagination(totalCount,page,size);

        //size(page-1)
        Integer offset = size*(paginationDTO.getCurrentPage()-1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,
                new RowBounds(offset, size));
//        List<Question> questions = questionMapper.profileList(id,offset,size);
        for (Question question : questions) {
            User user= userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {

        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void creatOrUpdate(Question question) {

        if(question.getId()==null){
            //创建插入数据
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //数据已经存在，则更新数据
//            question.setGmtModified(question.getGmtCreate());

            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion,questionExample);
        }
    }

}
