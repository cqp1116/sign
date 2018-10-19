package com.wenna.sign.lean.thymeleaf.controller;

import com.wenna.sign.lean.user.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {


    @ApiOperation(value = "跳转leaf页面" ,notes = "封装数据跳转到相应的页面")
    @RequestMapping(value = "/getLeaf" ,method =RequestMethod.GET)
    public String getThymeleaf(HttpServletRequest request, HttpServletResponse response, Model model){
        User obj  = new User("ddae","123455");
        obj.setSchool("这是学校");
        model.addAttribute("user",obj);
        return "/thymeleaf";
    }

}
