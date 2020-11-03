package com.mycloud.distributedDB.service;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/27
 */
public interface MemberService {

    void decMoney(Long memId, Integer money,Boolean flag);

}
