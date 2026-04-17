package com.blackwater.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/email")
public class EmailController {

    /**
     * 注入邮件工具类
     */
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;
    @Autowired
    private DefaultKaptcha captchaProducer;

    /**
     * 发送邮件
     * @param title 标题 （字符串）
     * @param text 主题内容（字符串）
     * @param receive 邮件的接受人（字符串）
     */
    @GetMapping("sendEmail")
    @ApiOperation(value = "发送邮件", notes = "发送邮件 接口")
    public void sendEmail(HttpServletRequest httpServletRequest,String receive) {
        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人
            mimeMessageHelper.setTo(receive);
            //邮件主题
            mimeMessageHelper.setSubject("黑臭水体");
            //随机生成验证码
            String retrieveEmailCode = captchaProducer.createText();
            System.out.println(retrieveEmailCode);
            //验证码存到session中
            httpServletRequest.getSession().setAttribute("retrieveEmailCode",retrieveEmailCode);
            //邮件内容
            mimeMessageHelper.setText("您接受到的验证码为:"+retrieveEmailCode);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());
            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功：" +sendMailer+"===>"+receive);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }


}
