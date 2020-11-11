package com.mycloud.umsserver.pojo.dto;

import com.mycloud.umsserver.pojo.po.Member;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/9
 */
@Getter
@Setter
public class MemberDto extends Member {


    /**
     * 登录类型
     */
    private int loginType;

    /**
     * 验证码
     */
    private String smsCode;
}
