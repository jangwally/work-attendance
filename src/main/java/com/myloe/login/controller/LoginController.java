package com.myloe.login.controller;

import com.myloe.commons.utils.MD5Utils;
import com.myloe.user.entity.User;
import com.myloe.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * 流程如下：
 *    1、首先调用Subject.login(token)进行登录，其会自动委托给Security Manager，调用之前必须通过SecurityUtils. setSecurityManager()设置；
 *   2、SecurityManager负责真正的身份验证逻辑；它会委托给Authenticator进行身份验证；
 *   3、Authenticator才是真正的身份验证者，Shiro API中核心的身份认证入口点，此处可以自定义插入自己的实现；
 *   4、Authenticator可能会委托给相应的AuthenticationStrategy进行多Realm身份验证，
 *      默认ModularRealmAuthenticator会调用AuthenticationStrategy进行多Realm身份验证；
 *   5、Authenticator会把相应的token传入Realm，从Realm获取身份验证信息，如果没有返回/抛出异常表示身份验证失败了。
 *   此处可以配置多个Realm，将按照相应的顺序及策略进行访问。
 *
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String login(){

        return "login";
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public String loginCheck(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //通过shiro来控制登录
        //将前端的用户和密码，放入令牌中。
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);

        //得到认证的主体
        Subject subject=SecurityUtils.getSubject();
        try {
            //可以捕获异常，来判断是否认证成功
            subject.login(usernamePasswordToken);

        }catch (AuthenticationException ace){
            return "login_fail";
        }

        return "login_success";

        //普通的登录
       /* User user=userService.findUserByName(username);
        if(user!=null){
            if(MD5Utils.checkPassword(password,user.getPassword())){
                request.getSession().setAttribute("LOGIN_USER",user);
                return "login_success";
            }else {
                return "login_fail";
            }
        }else{
            return "login_fail";
        }*/

    }


    //测试添加一个新用户，使用Postman工具完成
    @RequestMapping("/register")
    public void register(@RequestBody  User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.addUser(user);
    }

}
