package com.example.one.mapper;

import com.example.one.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ExpendMapper {
    @Update(" update QUESTION set VIEW_COUNT= VIEW_COUNT + #{viewCount}  where ID = #{id}")
    int incViewCount(Question record);
}
