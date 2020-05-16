package com.example.one.Service;

import com.example.one.Model.User;
import com.example.one.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;


    public User UserInforCreateUpdate(User user) {

        return null;

    }
}
