package com.example.one.mapper;

import com.example.one.Model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO one.question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
     void create(Question question);

    @Select("SELECT * FROM one.question limit #{offset},#{size}")
    List<Question> List(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM one.question")
    Integer Count();
    @Select("SELECT * FROM one.question where creator=#{id} limit #{offset},#{size}")
    List<Question> profileList(@Param(value = "id") Integer id,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    @Select("SELECT COUNT(1) FROM one.question where creator=#{id}")
    Integer profileCount(@Param(value = "id")Integer id);
    @Select("SELECT * FROM one.question where id=#{id}")
    Question getById(@Param("id") Integer id);

    @Update("update one.question set title=#{title},description=#{description},gmt_modified =#{gmtModified},tag=#{tag} where id=#{id}")
    void updatePassage(Question question);
}
