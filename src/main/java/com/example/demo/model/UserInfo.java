package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author fangkun
 * @date 2022/7/16 11:08
 * @description:
 */
@Data
@Entity(name = "t_user")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_age")
    private int age;

}
