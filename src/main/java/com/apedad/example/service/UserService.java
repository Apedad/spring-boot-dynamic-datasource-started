package com.apedad.example.service;

import com.apedad.example.entity.User;

import java.util.List;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
public interface UserService {
    List<User> listAll();

    int insert(User user);
}
