package com.blackwater.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Author: ljh
 * @ClassName KaptchaConfig
 * @date 2022/11/3 20:23
 * @Version 1.0
 */
@Component
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.border", "yes"); //是否有边框 yes有 no没有
        properties.put("kaptcha.textproducer.font.color", "blue"); //验证码字体颜色
        properties.put("kaptcha.image.width", "200"); //验证码图片的宽
        properties.put("kaptcha.image.height", "50"); //验证码图片的高
        properties.put("kaptcha.textproducer.font.size", "30"); //验证码字体大小
        properties.put("kaptcha.session.key", "verifyCode"); //存储在session中值的key
        properties.put("kaptcha.textproducer.char.length", "4"); //验证码字符个数
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

}
