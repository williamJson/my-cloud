package com.mycloud.umsserver.mapper;

import com.mycloud.umsserver.pojo.po.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/9/26
 */
@Repository
public interface MemberMapper {
    @Select("select * from ums_member where id =#{id}")
    Member selectById(@Param("id") Long id);

    void insert(Member member);
}
