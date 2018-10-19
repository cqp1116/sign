package com.wenna.sign.lean.redis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther 陈庆朋
 * @create 2018/10/12
 */
@Data
public class Student implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 登陆账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 图片
     */
    private String image;

    /**
     * 是否已经毕业（0：没有毕业 1：已经毕业）
     */
    private String isGraduated;

    /**
     * 微信OPENID
     */
    private String openid;

    /**
     * 微信昵称
     */
    private String wxNickname;

    /**
     * 微信账号
     */
    private String wxAccount;

    /**
     * 微信头像
     */
    private String wxHeadimage;

    /**
     * 是否删除（0：未删除 1：已删除）
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;
}
