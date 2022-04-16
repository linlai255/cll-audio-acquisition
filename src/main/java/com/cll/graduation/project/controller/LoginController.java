package com.cll.graduation.project.controller;


import com.cll.graduation.project.utils.CommonUtil;
import com.cll.graduation.project.utils.JwtUtil;
import com.cll.graduation.project.utils.RedisUtil;
import com.cll.graduation.project.vo.CurrentUser;
import com.cll.graduation.project.vo.LoginUser;
import com.cll.graduation.project.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/system/login")
@Slf4j
public class LoginController {

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String from;

    private static final String YZM = "yzm";


    /**
     * 发送验证码
     *
     * @param email
     * @return
     */
    @PostMapping("/getVerificationCode")
    public Result getVerificationCode(@RequestParam String email) {
        String random = CommonUtil.getRandom6();
        String key = YZM + email;
        redisUtil.set(key, random, 60);
        Object o = redisUtil.get(key);
        log.info("obj: {}", o);
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(email);
        //邮件标题
        message.setSubject("登录验证码");
        //邮件内容
        message.setText("您正在尝试登录CLL语音采集系统，您的验证码为: " + random);
        log.info("start send mail param: {}", message);
        mailSender.send(message);
        LoginUser loginUser = CurrentUser.get();
        return Result.success(random);
    }


    /**
     * 根据邮箱和验证码进行登录并且返回token
     *
     * @param email
     * @param code
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam String email,
                        @RequestParam String code) {
        Object o = redisUtil.get(YZM + email);
        if (o == null) {
            return Result.fail("验证码已失效");
        }
        if (!o.toString().equals(code)) {
            return Result.fail("验证码错误");
        }
        String s = JwtUtil.generateJwtToken();
        return Result.success(s);
    }


}
