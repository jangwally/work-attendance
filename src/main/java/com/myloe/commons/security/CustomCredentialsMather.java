package com.myloe.commons.security;

import com.myloe.commons.utils.MD5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 自定义的凭证（Credentials）验证器
 *
 *  principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。一个主体可以有多个principals，
 *  但只有一个Primary principals，一般是用户名/密码/手机号。
 *
 *  credentials：证明/凭证，即只有主体知道的安全值，如密码/数字证书等。
 *
 *
 *
 */
public class CustomCredentialsMather extends SimpleCredentialsMatcher{

    //手动，重写父类的方法，实现自己的凭证验证规则
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){

        try {
            UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;

            //得到的字节数组，转化成字符串,这个是登录密码
            String password=String.valueOf(usernamePasswordToken.getPassword());

            //按照自定义的加密规则，加密密码
            String tokenCredentials=MD5Utils.encryptPassword(password);

            //得到数据库中的密码，info为自定义Realm传过来的，里面包含数据库用户密码的信息
            String accountCredentials= (String) this.getCredentials(info);

            //返回密码比对结果
            //至此，身份和凭证比对结束
            return this.equals(tokenCredentials,accountCredentials);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;

    }
}
