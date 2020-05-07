package com.example.one.mapper;

import com.example.one.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER.USER (name ,account_id ,token, gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void Insert(User user);

}
