package com.mycloud.distributedDB.controller;

import com.mycloud.distributedDB.mapper.productmapper.POrderMapper;
import com.mycloud.distributedDB.mapper.umsmapper.MemberMapper;
import com.mycloud.distributedDB.service.MemberService;
import com.mycloud.distributedDB.service.OrderService;
import com.mycloud.distributedDB.util.IdGenerator;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/10/26
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private POrderMapper orderMapper;

    @GetMapping("/findAll")
    public Map findAll() {
        Map<String, List> result = new HashMap<>();
        result.put("members", memberMapper.findAll());
        result.put("orders", orderMapper.findAll());
        return result;
    }


    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderService orderService;


    @ShardingTransactionType(TransactionType.XA)
    @PostMapping("/commitOrder/{memId}/{money}/{flag}")
    @Transactional(rollbackFor = Exception.class)
    public String commitOrder(@PathVariable("memId") Long memId, @PathVariable("money") Integer money, @PathVariable("flag") Boolean flag) {
        //这里会存在分布式事物问题，因为这里存在多个数据库链接并且不是同一个库
        //所以要使用分布式事物，但是这里并没有使用分布式事物但是当异常发生时，多个库仍然能
        //正常回滚，这是因为sharding-jdbc会在发生异常的时候，遍历所有的真实链接并调用rollback进行回滚
        memberMapper.findAll();
        orderService.createOrder(memId, money);
        memberService.decMoney(memId, money, flag);


        return "ok";
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/redis")
    public Map redis(String content) {
        Map<String, Object> params = new HashMap<>();
        Long id = IdGenerator.next();
        params.put("content", content);
        params.put("create_time", new Date().toString());
        params.put("id", id.toString());
        redisTemplate.opsForHash().putAll(id.toString(), params);

        redisTemplate.opsForHash().entries(id.toString());
//        redisTemplate.opsForHash().putIfAbsent()
        return params;
    }
}
