package com.example.demo;

import com.example.demo.model.UserInfo;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author fangkun
 * @date 2022/7/16 10:47
 * @description:
 */
@RestController
public class TestApi {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "test")
    @ResponseBody
    public Object test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(1);
        userInfo.setUserName("test");
        return userService.saveUser(Arrays.asList(userInfo));
    }

    @GetMapping(value = "get/{userId}")
    @ResponseBody
    public Object get(@PathVariable("userId") long userId) {
        return userService.queryUser(userId);
    }
}
