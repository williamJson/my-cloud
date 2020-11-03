package com.mycloud.umsserver.controller;

import com.mycloud.comm.pojo.R;
import com.mycloud.comm.util.IdGenerator;
import com.mycloud.umsserver.mapper.MemberMapper;
import com.mycloud.umsserver.pojo.po.Member;
import com.mycloud.umsserver.service.MemberService;
import com.mycloud.umsserver.service.SmsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wyq on 2020/9/26.
 *
 * @auther wyq
 * qq:342622023
 */

@RestController
public class MemberController {


    /**
     * 根据会员id获取会员详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getMemberInfo/{id}")
        public String getMemberInfo(@PathVariable("id") Long id) {
        System.out.println("this is member id=" + id);
        return id + "";
    }


    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/memeber/{id}")
    public Member getMember(@PathVariable("id") Long id) {
        return memberMapper.selectById(id);
    }


    @Autowired
    private MemberService memberService;

    @PostMapping("/member/register")
    public R<Member> register(@RequestBody Member member) {
        return R.ok(memberService.register(member));
    }


    @Autowired
    private SmsService smsService;

    @PostMapping("/sendSms/{mobile}/{smsType}")
    public R<String> sendSms(@PathVariable("mobile") String mobile, @PathVariable("smsType") String smsType) {
        smsService.sendSms(mobile, smsType);
        return R.ok("ok");
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/syncSend/{exchange}/{routingkey}/{msg}")
    public R<String> syncSend(@PathVariable("exchange") String exchange, @PathVariable("routingkey") String routingkey, @PathVariable("msg") String msg) {

        Map<String, String> data = new HashMap<>();
        data.put("Id", IdGenerator.next() + "");
        data.put("content", msg);
        //发送的消息消费者接受后才会返回，不然会一直阻塞
        Object result = rabbitTemplate.convertSendAndReceive(exchange, routingkey, data);
        return R.ok("ok");
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/rset/{key}/{value}")
    public R<String> redisSet(@PathVariable("key") String key, @PathVariable("value") String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return R.ok("ok");
    }

    @PostMapping("/rget/{key}")
    public R<String> redisGet(@PathVariable("key") String key) {
        return R.ok(stringRedisTemplate.opsForValue().get(key));
    }
}
