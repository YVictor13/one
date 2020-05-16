package com.example.one.mapper;

import com.example.one.Dto.QuestionDTO;
import com.example.one.Model.Question;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    QuestionDTO geiById(Integer id);
}
