package com.mycloud.umsserver.controller;

import com.mycloud.umsserver.pojo.R;
import com.mycloud.umsserver.pojo.po.Member;
import com.mycloud.umsserver.mapper.MemberMapper;
import com.mycloud.umsserver.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wyq on 2020/9/26.
 *
 * @auther wyq
 * qq:342622023
 */

@RestController
public class MemberController {


    /**
     * 根据会员id获取会员详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getMemberInfo/{id}")
    public String getMemberInfo(@PathVariable("id") Long id) {
        System.out.println("this is member id=" + id);
        return id + "";
    }


    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/memeber/{id}")
    public Member getMember(@PathVariable("id") Long id) {
        return memberMapper.selectById(id);
    }


    @Autowired
    private MemberService memberService;

    @PostMapping("/member/register")
    public R<Member> register(@RequestBody Member member) {
        return R.ok(memberService.register(member));
    }
}
