package com.apedad.example.dao;

import com.apedad.example.entity.UserInfo;

import java.util.List;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
public interface UserInfoMapper {
    List<UserInfo> listAll();

    int insert(UserInfo userInfo);
}
