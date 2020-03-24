package com.zshuai.dao;

import com.zshuai.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 /**
 * Created by zshuai
 *
 * @Date :2020/3/19 15:06 PM
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
