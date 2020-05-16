package com.example.one.mapper;

import com.example.one.Model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Select("select * from one.user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Insert("INSERT INTO one.USER (name ,account_id ,token, gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void Insert(User user);
    @Select("select * from one.user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from one.user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update one.user set  name=#{name}, token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where account_id = #{accountId}")
    void updateInfo(User dbUser);
}
