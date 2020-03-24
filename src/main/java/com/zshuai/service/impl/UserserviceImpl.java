package com.zshuai.service.impl;

import com.zshuai.dao.UserRepository;
import com.zshuai.pojo.User;
import com.zshuai.service.Userservice;
import com.zshuai.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 6:04 PM
 * @Version 1.0
 **/
@Service
public class UserserviceImpl  implements Userservice {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
