package com.myloe.user.controller;

import com.myloe.user.entity.User;
import com.myloe.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String getHome(){

        return "home";
    }


    /**
     * 前面登录使用shiro控制的，同时也将用户登录信息存入到了session中，这里直接取。
     * @return
     */
    @RequestMapping("/userinfo")
    @ResponseBody
    public User getUserInfo(){
        User user= (User) SecurityUtils.getSubject().getSession().getAttribute("LOING_USER");

        return user;
    }
}
