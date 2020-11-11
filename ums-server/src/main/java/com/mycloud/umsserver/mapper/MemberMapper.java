package com.mycloud.umsserver.mapper;

import com.mycloud.umsserver.pojo.po.Member;
import org.apache.ibatis.annotations.Insert;
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


    /**
     * 查询账户名是否已存在
     *
     * @param userName
     * @return
     */
    @Select("select count(*) from ums_member where u_name=#{userName}")
    int isUNameExists(@Param("u_name") String userName);

    /**
     * 查询电话号码是否已被绑定
     *
     * @param mobile
     * @return
     */
    @Select("select count(*) from ums_member where mobile=#{mobile}")
    int isMobileBound(@Param("mobile") String mobile);


    /**
     * 插入
     *
     * @param member
     * @return
     */
    int insert(Member member);


    @Select("select * from ums_member where u_name =#{u_name}")
    Member getByUName(@Param("u_name") String uName);

    @Select("select * from ums_member where mobile=#{mobile}")
    Member getByMobile(@Param("mobile") String mobile);
}
