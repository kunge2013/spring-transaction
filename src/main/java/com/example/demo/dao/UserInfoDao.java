package com.example.demo.dao;

import com.example.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    @Query("select u from t_user  u where u.userName =:userName")
    List<UserInfo> getUserInfoByByUserName(String userName);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    default  <S extends UserInfo> List<S> saveList(Iterable<S> entities) {
        return saveAll(entities);
    }
}
