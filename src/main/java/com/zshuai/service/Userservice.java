package com.zshuai.service;

import com.zshuai.pojo.User;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 6:04 PM
 * @Version 1.0
 **/
public interface Userservice {
    User checkUser(String username, String password);
}
