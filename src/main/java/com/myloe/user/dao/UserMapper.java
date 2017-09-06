package com.myloe.user.dao;

import com.myloe.user.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByName(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}