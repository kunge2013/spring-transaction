package com.example.demo.service;

import com.example.demo.model.UserInfo;

import java.util.List;

/**
 * @author fangkun
 * @date 2022/7/16 11:07
 * @description:
 */

public interface IUserService {
    List<UserInfo>  saveUser(List<UserInfo> userInfoList);
    UserInfo queryUser(long id);
}
