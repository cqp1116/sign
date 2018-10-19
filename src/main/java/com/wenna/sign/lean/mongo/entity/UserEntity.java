package com.wenna.sign.lean.mongo.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @auther 陈庆朋
 * @create 2018/10/10
 */
@Data
public class UserEntity {
    private Long id;
    private String name;
    private int age;
    private String sex;
}
