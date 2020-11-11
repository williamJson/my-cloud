package com.mycloud.umsserver.controller;

import com.mycloud.comm.pojo.R;
import com.mycloud.umsserver.pojo.dto.MemberDto;
import com.mycloud.umsserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/9
 */
@RestController
public class AccountController {


    @Autowired
    private AccountService accountService;

    @PostMapping("/account/register")
    public R<String> registerAccount(@RequestBody MemberDto memberDto) {
        accountService.register(memberDto);
        return R.ok("success");
    }


    @PostMapping("/login")
    public R login(@RequestBody MemberDto memberDto) {
        return R.ok(accountService.login(memberDto));
    }
}
