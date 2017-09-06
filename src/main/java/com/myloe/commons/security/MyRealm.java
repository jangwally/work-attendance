package com.myloe.commons.security;

import com.myloe.user.entity.User;
import com.myloe.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的验证主体（Subject）的数据源（Realm）
 *
 *
 * Realm：域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，
 * 那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；
 * 可以把Realm看成DataSource，即安全数据源。
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权方法,登录认证通过后的权限查询函数.
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    //认证方法,登录认证回调函数,登录时调用.
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //authenticationToken对象转化成UsernamePasswordToken对象。
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        //从UsernamePasswordToken对象取得登录的用户名
        String username=usernamePasswordToken.getUsername();

        //根据上面得到的用户名，从数据库查询到对象
        User user=userService.findUserByName(username);

        //判断是否存在这个用户
        if(user==null){
            return null;
        }else{

            //把得到的数据库信息放入到AuthenticationInfo对象中
            AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());

            //认证后，将结果放在session中
            SecurityUtils.getSubject().getSession().setAttribute("LOING_USER",user);


            //返回信息，之后会自动调用凭证验证
            return authenticationInfo;
        }

    }
}
