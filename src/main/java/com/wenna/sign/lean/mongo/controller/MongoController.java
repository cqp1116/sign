package com.wenna.sign.lean.mongo.controller;

import com.wenna.sign.lean.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther 陈庆朋
 * @create 2018/10/10
 */
@RestController
@RequestMapping("mongo")
public class MongoController {


    @Autowired
    private UserRepository userRepository;
    /**
     * 添加用户信息
     * @return
     */
    @GetMapping("addUser")
    public String addUser(){

        return "";
    }

}
