package com.mycloud.distributedDB.service.impl;

import com.mycloud.distributedDB.mapper.umsmapper.MemberMapper;
import com.mycloud.distributedDB.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/27
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void decMoney(Long memId, Integer money, Boolean flag) {
        //修改余额
        memberMapper.decMoney(memId, money);

        if (flag) {
            throw new RuntimeException("test transaction rollback...");
        }
    }
}
