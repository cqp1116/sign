package com.wenna.sign.lean.user.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSONObject;
import com.wenna.sign.base.tools.Page;
import com.wenna.sign.base.tools.ServletUtil;
import com.wenna.sign.base.tools.StringUtil;
import com.wenna.sign.lean.redis.service.RedisService;
import com.wenna.sign.lean.user.pojo.LearnResouce;
import com.wenna.sign.lean.user.pojo.User;
import com.wenna.sign.lean.user.service.LearnService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LearnResourceController {

    @Autowired
    private LearnService learnService;
    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public ModelAndView home(ModelAndView mv){
        mv.setViewName("/login");
        return mv;
    }

    /**
     *登录操作
     **/
    @ApiOperation(value = "这是一个登陆")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        if(!userName.equals("") && password!=""){
            User user =new User(userName,password);
            user.setSchool("济南辅仁第一中学");
            request.getSession().setAttribute("user",user);
            redisService.add("loginUser",user,111L);
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }


    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("/index");
        mv.addObject("name","adb");
        return mv;
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/learn")
    public String learn(){
        return "/learn-resource";
    }


    @RequestMapping(value = "/learn/queryLeanList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryLearnList(HttpServletRequest request ,HttpServletResponse response){
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("author", author);
        params.put("title", title);
        Page pageObj =learnService.queryLearnResouceList(params);
        List<Map<String, Object>> learnList=pageObj.getResultList();
        JSONObject jo=new JSONObject();
        jo.put("rows", learnList);
        jo.put("total", pageObj.getTotalPages());
        jo.put("records", pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200, jo, response);
    }
    /**
     * 新添教程
     * @param request
     * @param response
     */
    @RequestMapping(value = "/learn/add",method = RequestMethod.POST)
    public void addLearn(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if(StringUtil.isNull(author)){
            result.put("message","作者不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(StringUtil.isNull(title)){
            result.put("message","教程名称不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(StringUtil.isNull(url)){
            result.put("message","地址不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        LearnResouce learnResouce = new LearnResouce();
        learnResouce.setAuthor(author);
        learnResouce.setTitle(title);
        learnResouce.setUrl(url);
        int index=learnService.add(learnResouce);
        System.out.println("结果="+index);
        if(index>0){
            result.put("message","教程信息添加成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息添加失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
    /**
     * 修改教程
     * @param request
     * @param response
     */
    @RequestMapping(value = "/learn/update",method = RequestMethod.POST)
    public void updateLearn(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
        String id = request.getParameter("id");
        LearnResouce learnResouce=learnService.queryLearnResouceById(Long.valueOf(id));
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if(StringUtil.isNull(author)){
            result.put("message","作者不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(StringUtil.isNull(title)){
            result.put("message","教程名称不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(StringUtil.isNull(url)){
            result.put("message","地址不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        learnResouce.setAuthor(author);
        learnResouce.setTitle(title);
        learnResouce.setUrl(url);
        int index=learnService.update(learnResouce);
        System.out.println("修改结果="+index);
        if(index>0){
            result.put("message","教程信息修改成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息修改失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
    /**
     * 删除教程
     * @param request
     * @param response
     */
    @RequestMapping(value="/learn/delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(HttpServletRequest request ,HttpServletResponse response){
        String ids = request.getParameter("ids");
        System.out.println("ids==="+ids);
        JSONObject result = new JSONObject();
        //删除操作
        int index = learnService.deleteByIds(ids);
        if(index>0){
            result.put("message","教程信息删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }


}