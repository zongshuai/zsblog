package com.zshuai.controller.admin;

import com.zshuai.pojo.User;
import com.zshuai.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by zshuai
 *
 * @Date :2020/3/19 5:56 PM
 * @Version 1.0
 **/
@Controller
@RequestMapping("/login")
//@Api(value = "个人管理登录", description = "后台入口")
public class LoginController {


    @Autowired
    Userservice userservice;
    /**
     * 显示用户登录页面
     * @return
     */
    @GetMapping
    //@ApiOperation(httpMethod = "GET", value = "跳转到登录页面")
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("login")
    //@ApiOperation(httpMethod = "POST", value = "用户登录，根据前端输入的数据进行登录校验")
    public String Login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userservice.checkUser(username, password);
        if (user != null){
            System.out.println(user.getPassword());
            user.setPassword(null);
            session.setAttribute("user", user);//通过session将用户信息传递到前台
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/login";
        }
    }

    @GetMapping("logout")
    //@ApiOperation(httpMethod = "GET", value = "用户退出")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }


}
