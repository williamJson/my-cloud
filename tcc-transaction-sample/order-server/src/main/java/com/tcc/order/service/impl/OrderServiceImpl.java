package com.tcc.order.service.impl;

import com.tcc.order.service.OrderService;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/3
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Compensable(confirmMethod = "confirmOrder", cancelMethod = "cancelOrder")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void tryOrder() {

    }

    @Override
    public void confirmOrder() {

    }

    @Override
    public void cancelOrder() {

    }
}
