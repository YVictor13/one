package com.example.one.Service;

import com.example.one.enums.CommentTypeEnum;
import com.example.one.exception.CustomizeErrorCode;
import com.example.one.exception.CustomizeException;
import com.example.one.mapper.CommentMapper;
import com.example.one.mapper.ExpendMapper;
import com.example.one.mapper.QuestionMapper;
import com.example.one.model.Comment;
import com.example.one.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private ExpendMapper expendMapper;

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
}
