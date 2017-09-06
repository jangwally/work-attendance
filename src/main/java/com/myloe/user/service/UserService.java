package com.myloe.user.service;



import com.myloe.user.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    User findUserByName(String username);

    void addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
