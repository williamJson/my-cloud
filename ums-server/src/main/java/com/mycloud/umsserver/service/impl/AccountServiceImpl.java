package com.mycloud.umsserver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.mycloud.comm.util.IdGenerator;
import com.mycloud.umsserver.mapper.MemberMapper;
import com.mycloud.umsserver.pojo.dto.MemberDto;
import com.mycloud.umsserver.pojo.po.Member;
import com.mycloud.umsserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/9
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    /**
     * 账户名正则
     */
    private static final String ACCOUNT_REG = "";
    /**
     * 密码正则
     */
    private static final String PASS_REG = "";
    /**
     * 验证码正则
     */
    private static final String V_CODE_REG = "";

    /**
     * 手机号正则
     */
    private static final String MOBILE_REG = "";


    @Autowired
    MemberMapper memberMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(MemberDto memberDto) {
        //校验用户名格式
//        if (!ReUtil.isMatch(ACCOUNT_REG, memberDto.getUName())) {
//            throw new RuntimeException("user name format is error...");
//        }
        //校验密码格式
//        if (ReUtil.isMatch(PASS_REG, memberDto.getUPass())) {
//            throw new RuntimeException("password format is error...");
//        }
        //验证验证码
        //校验手机号格式

        //检查用户名是否已被占用
        if (memberMapper.isUNameExists(memberDto.getUserName()) > 0) {
            throw new RuntimeException("user name is bind used...");
        }
        //检查手机号是否已被绑定
        if (memberMapper.isMobileBound(memberDto.getMobile()) > 0) {
            throw new RuntimeException("mobile is already bound...");
        }

        Member member = new Member();
        //属性copy
        BeanUtil.copyProperties(memberDto, member, "id");
        member.setId(IdGenerator.next());
        member.setSlat(generateSlat());
        //生成32位长度字符串
        member.setUserPass(SecureUtil.md5(member.getUserName() + member.getSlat()));
        member.setState(1);
        if (memberMapper.insert(member) <= 0) {
            throw new RuntimeException("insert into fail...");
        }
    }


    /**
     * 生成密码盐
     *
     * @return
     */
    private String generateSlat() {
        return RandomUtil.randomString(8);
    }

    
    /**
     * 登录
     * 登录方式分为多种
     * 1 账号密码登录
     * 2 手机号短信登录
     * 3 扫码登录
     * 4 第三方账户登录
     *
     * @param memberDto
     */
    @Override
    public Member login(MemberDto memberDto) {


        Member result = null;

        switch (memberDto.getLoginType()) {
            //账号密码登录
            case 1:
                result = loginByPass(memberDto);
                break;
            //手机号登录
            case 2:
                result = loginByMobile(memberDto);
                break;
            //扫码登录
            case 3:
                result = loginByQRCode(memberDto);
                break;
            default:
                throw new RuntimeException("登录失败");
        }

        //发布登录通知
        //修改最后登录时间，最后登录ip等

        if (null == result) {
            throw new RuntimeException("登录失败");
        }
        return result;
    }

    /**
     * 通过账户密码登录
     *
     * @param memberDto
     */
    private Member loginByPass(MemberDto memberDto) {

        Member member = memberMapper.getByUName(memberDto.getUserName());
        if (null == member) {
            throw new RuntimeException("账号密码错误");
        }

        if (!member.getUserPass().equals(SecureUtil.md5(memberDto.getUserPass() + member.getSlat()))) {
            throw new RuntimeException("账号密码错误");
        }

        return member;

    }

    /**
     * 通过手机号登录
     *
     * @param memberDto
     */
    private Member loginByMobile(MemberDto memberDto) {

        Member member = memberMapper.getByMobile(memberDto.getUserName());
        if (null == member) {
            throw new RuntimeException("账号密码错误");
        }

        //check sms code

        return member;
    }


    /**
     * 扫码登录
     *
     * @param memberDto
     */
    private Member loginByQRCode(MemberDto memberDto) {
        throw new RuntimeException(" not support exception");
    }
}
