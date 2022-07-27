package com.example.demo.dao;

import com.example.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    @Query("select u from t_user  u where u.userName =:userName")
    List<UserInfo> getUserInfoByByUserName(String userName);
}
