package com.mycloud.umsserver.pojo.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 功能说明：
 * 用户会员do
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/9/27
 */
@Getter
@Setter
public class Member extends BaseEntity {


    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPass;

    /**
     * 密码盐
     */
    private String slat;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatarUrl;


    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 上次登录ip
     */
    private String lastLoginIp;

}
