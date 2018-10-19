package com.wenna.sign.lean.redis.controller;

import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.wenna.sign.lean.redis.pojo.Student;
import com.wenna.sign.lean.redis.service.RedisService;
import com.wenna.sign.lean.user.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;


    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @GetMapping("/user")
    public void getUser(HttpServletRequest request, HttpServletResponse response){
        User user = new User("张三","密码");
        user.setSchool("上海");
        redisService.add("loginUser",user,111L);

        User  uobj =  (User)redisService.get("loginUser",User.class);
        this.printStr("当前登陆用户为"+uobj.getUserName()+"学校为"+uobj.getSchool(),null,response);

    }



    @ApiOperation(value = "redis 对于用户操作",notes = "将学生信息存入redis进行缓存")
    @GetMapping("/opStr")
    public void operateStr(HttpServletRequest request,HttpServletResponse response){
        Student stu = new Student();
        stu.setAccount("chenqp");
        stu.setName("问呐平台");
        stu.setWxAccount("wxstu");

        redisService.operateStr(stu);
    }


    @ApiOperation(value = "redis操作集合数据",notes = "将集合数据存入redis")
    @GetMapping("/opSet")
    public void operateSet(HttpServletRequest request, HttpserverMessages response){
        Set set = new HashSet();
        set.add("this is first message");
        set.add("this is second ");
        set.add("it's all crap");
        redisService.operateSet("crapSet" ,set);
    }


    /**
     * 返回前台数据
     * @param str
     * @param contentType
     * @param response
     */
    void printStr(String str,String contentType,HttpServletResponse response){
        try{
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(str);
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
