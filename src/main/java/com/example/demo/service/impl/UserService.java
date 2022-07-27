package com.example.demo.service.impl;

import com.example.demo.dao.UserInfoDao;
import com.example.demo.model.UserInfo;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationUtils;

import java.util.Arrays;
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
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserInfoDao userInfoDao;

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Transactional
    @Override
    public List<UserInfo>  saveUser(List<UserInfo> userInfoList) {
        userInfoDao.saveAll(userInfoList);

        TransactionSynchronizationUtils.invokeAfterCommit(Collections.singletonList(new TransactionSynchronization() {

            @Override
            public void afterCommit() {

                if (atomicInteger.incrementAndGet() <= 2) {
                      saveUser(Arrays.asList(new UserInfo()));
                } else {
                    atomicInteger = new AtomicInteger(0);
                    return;
                }
            }
        }));
        log.info("atomicInteger = {}", atomicInteger.get());
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
