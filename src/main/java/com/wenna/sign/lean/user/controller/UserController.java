package com.wenna.sign.lean.user.controller;

import com.wenna.sign.lean.user.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String folder = "D:\\IDEASPS\\imodelSecurity\\imodel-security-demo\\src\\main\\java\\com\\imodel\\demo\\User\\controller";

    @GetMapping
    @ApiOperation(value = "查询用户信息")
    public List<User> query(User user, @PageableDefault(page = 2,size = 15,sort = "userSex,desc") Pageable page){
        logger.error("输出error信息");
        List<User> list = new ArrayList<>();
        User join = new User("张三","zhangsan");
        list.add(new User("王五","wangwu"));
        list.add(join);
        return list;
    }


    /**
     * :\\  为参数添加
     * 正则表达式
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "根据用户id得到相应用户数据")
    public User getInfo(@ApiParam(value = "用户id") @PathVariable String id){
        System.out.println(id+"================");
        if(id .equals("1")){
            User user = new User("张三","lisi");
            user.setUserName("tome");
            return user;
        }else{
            return null;
        }
    }

    /**
     * 执行删除操作
     * @param id
     */
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "根据用户id删除数据")
    public void delInfo(@ApiParam(value = "用户的id") @PathVariable String id){
        System.out.println(id+"==================");
    }

    /**
     * 在实体类中加入验证处理
     * @param user
     * @param errors
     */
    @PostMapping
    @ApiOperation(value = "添加用户信息")
    public User addInfo(@Valid @RequestBody User user, BindingResult errors){
        //jpa对数据实体类进行异常处理
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField()+"===="+fieldError.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            });
        }
        user.setId((long)1);
        return user;
    }


    /**
     * 用putmapping进行修改数据
     * @param user
     * @param errors
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改用户信息")
    public User updateInfo(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setSchool("这事修改的学校");
        return user;
    }

    /**
     * 下载的get请求
     * @param id
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/down/{id}")
    public void download(@PathVariable String id , HttpServletRequest request, HttpServletResponse response)throws  Exception{
        try(InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
            OutputStream outputStream = response.getOutputStream();
        ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }
}
