package com.apedad.example.dao;

import com.apedad.example.entity.User;

import java.util.List;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
public interface UserMapper {
    List<User> listAll();

    int insert(User user);
}
