package com.mycloud.umsserver.service;

import com.mycloud.umsserver.pojo.dto.MemberDto;
import com.mycloud.umsserver.pojo.po.Member;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/9
 */
public interface AccountService {

    /**
     * 注册新用户
     *
     * @param memberDto
     */
    void register(MemberDto memberDto);

    /**
     * 用户登录
     *
     * @param memberDto
     */
    Member login(MemberDto memberDto);
}
