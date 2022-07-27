package com.example.demo.service.impl;

import com.example.demo.dao.UserInfoDao;
import com.example.demo.model.UserInfo;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fangkun
 * @date 2022/7/16 11:07
 * @description:
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserInfoDao userInfoDao;

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Transactional
    @Override
    public List<UserInfo>  saveUser(List<UserInfo> userInfoList) {

        TransactionSynchronizationUtils.invokeAfterCommit(Collections.singletonList(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                ;
                System.out.println("xxxxx");
                if (atomicInteger.incrementAndGet() <= 2) {
                      saveUser(userInfoList);
                } else {
                    atomicInteger = new AtomicInteger(0);
                    return;
                }
            }
        }));
        userInfoDao.saveAll(userInfoList);
        return userInfoList;
    }

    @Override
    public UserInfo queryUser(long id) {
        Optional<UserInfo> optional = userInfoDao.findById(id);
        boolean present = optional.isPresent();
        if (present) return optional.get();
        return null;
    }
}
