package com.tcc.account.mapper;


import com.tcc.account.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/26
 */
@Repository
public interface MemberMapper {

    @Select("select * from ums_member where id =#{id}")
    Member selectById(@Param("id") Long id);


    @Select("select * from ums_member")
    List<Member> findAll();


    int decMoney(@Param("id") Long id, @Param("money") Integer money);
}
