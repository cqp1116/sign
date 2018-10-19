package com.wenna.sign.base.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  cqp1116
 */
@RestController
@RequestMapping("/getPropert")
public class readPropertController {

    @Value("${com.wenna.sign}")
    private String sign;

    @RequestMapping(value = "/propert.do",method = RequestMethod.GET)
    public String propert(){
        System.out.println(sign);
        return sign+"===========";
    }
}
