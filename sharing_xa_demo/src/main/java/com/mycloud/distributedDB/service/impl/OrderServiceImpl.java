package com.mycloud.distributedDB.service.impl;

import com.mycloud.distributedDB.mapper.productmapper.POrderMapper;
import com.mycloud.distributedDB.pojo.po.Order;
import com.mycloud.distributedDB.service.OrderService;
import com.mycloud.distributedDB.util.IdGenerator;
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
public class OrderServiceImpl implements OrderService {


    @Autowired
    private POrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(Long memId, Integer money) {
        Order order = new Order();
        order.setId(IdGenerator.next());
        order.setTotalMoney(money);
        order.setUserId(memId);
        //插入订单记录
        orderMapper.insert(order);
    }
}
