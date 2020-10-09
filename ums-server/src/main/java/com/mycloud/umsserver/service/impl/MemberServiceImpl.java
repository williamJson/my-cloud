package com.mycloud.umsserver.service.impl;

import com.mycloud.comm.util.IdGenerator;
import com.mycloud.umsserver.mapper.MemberMapper;
import com.mycloud.umsserver.pojo.po.Member;
import com.mycloud.umsserver.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/9
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member register(Member member) {
        member.setId(IdGenerator.next());
        memberMapper.insert(member);
        Thread.currentThread().isInterrupted();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return member;
    }
}
