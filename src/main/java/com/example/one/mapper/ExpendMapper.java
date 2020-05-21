package com.example.one.mapper;

import com.example.one.model.Comment;
import com.example.one.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ExpendMapper {
    @Update(" update QUESTION set VIEW_COUNT= VIEW_COUNT + #{viewCount}  where ID = #{id}")
    void incViewCount(Question record);

    @Update("update QUESTION set COMMENT_COUNT = COMMENT_COUNT + #{commentCount} where ID = #{id}")
    void incCommentCount(Question record);

    @Update("update COMMENT set COMMENT_COUNT = COMMENT_COUNT + #{commentCount} where ID = #{id}")
    void incToCommentCount(Comment record);
}
