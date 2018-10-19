package com.wenna.sign.lean.user.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tengj on 2017/3/29.
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false)
    private String school;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
