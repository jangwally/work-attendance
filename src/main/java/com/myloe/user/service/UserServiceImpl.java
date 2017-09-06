package com.myloe.user.service;

import com.myloe.commons.utils.MD5Utils;
import com.myloe.user.dao.UserMapper;
import com.myloe.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@Service()
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findUserByName(String username) {
        User user = userMapper.selectByName(username);
        return user;
    }

    //测试添加新用户，密码加密
    @Override
    public void addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
            user.setPassword(MD5Utils.encryptPassword(user.getPassword()));
            userMapper.insertSelective(user);
    }




}
