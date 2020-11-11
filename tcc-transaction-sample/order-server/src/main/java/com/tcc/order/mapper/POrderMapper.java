package com.tcc.order.mapper;


import com.tcc.order.entity.Order;
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
public interface POrderMapper {


    @Select("select * from p_order")
    List<Order> findAll();


    int insert(Order order);

}
