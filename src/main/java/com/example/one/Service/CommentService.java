package com.example.one.Service;

import com.example.one.Dto.CommentDTO;
import com.example.one.enums.CommentTypeEnum;
import com.example.one.exception.CustomizeErrorCode;
import com.example.one.exception.CustomizeException;
import com.example.one.mapper.CommentMapper;
import com.example.one.mapper.ExpendMapper;
import com.example.one.mapper.QuestionMapper;
import com.example.one.mapper.UserMapper;
import com.example.one.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private ExpendMapper expendMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARM_NOT_FOUND);
        }
        if (comment.getType() != null && !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException((CustomizeErrorCode.TYPE_PARM_WRONG));
        }
        if (comment.getType() ==CommentTypeEnum.COMMENT.getType() ){
            //回复评论
            Comment dbComment=commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_EXIST);
            }else{
                commentMapper.insert(comment);
                Comment parentComment=commentMapper.selectByPrimaryKey(comment.getParentId());
                parentComment.setCommentCount(1L);
                expendMapper.incToCommentCount(parentComment);
            }
        }else{
            //回复问题
            Question question=questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null ){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            expendMapper.incCommentCount(question);
        }
    }

    public List<CommentDTO> ListByQuestionId(Long id) {
//        抽取变量的快捷键： ctrl + Alt + v
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size() == 0){
            return new ArrayList<>();
        }
        //使用Lamba去重评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long,User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment 为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;

    }
}
