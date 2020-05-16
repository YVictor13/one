package com.example.one.Service;

import com.example.one.Model.User;
import com.example.one.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;


    public void createOrUpdate(User user) {

//        快速回退到上一个操作页面快捷键： ctrl + alt + 左右键
         User dbUser =userMapper.findByAccountId(user.getAccountId());
         if(null!=dbUser){
             //更新数据
             dbUser.setGmtModified(System.currentTimeMillis());
             dbUser.setAvatarUrl(user.getAvatarUrl());
             dbUser.setName(user.getName());
             dbUser.setToken(user.getToken());
             userMapper.updateInfo(dbUser);
         }else{
             //插入数据
             user.setGmtModified(System.currentTimeMillis());
             user.setGmtCreate(user.getGmtCreate());
             userMapper.Insert(user);

         }



    }
}
