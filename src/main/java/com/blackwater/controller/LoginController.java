package com.blackwater.controller;

import com.blackwater.config.IPConfig;
import com.blackwater.config.redis.RedisUtil;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Md5Util;
import com.blackwater.config.until.Result;
import com.blackwater.entity.User.Login;
import com.blackwater.entity.User.Register;
import com.blackwater.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@Api(tags = "用户登录")
@RequestMapping("/Account/")
@Slf4j
public class LoginController {

    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    private final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    /**
     * 登录接口
     *
     * @param account 包含账号密码
     * @return 返回Token
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录账号", notes = "登录账号接口")
    @ApiResponses({
            @ApiResponse(code = 30001, message = "登录成功状态码"),
            @ApiResponse(code = 30000, message = "登录失败状态码")
    })
    public Result login(HttpServletRequest httpServletRequest, @RequestBody Login account) {
        return userService.login(httpServletRequest,account);
    }


    /**
     * 退出接口
     *
     * @return 退出是否成功
     */
    @GetMapping("/logout")
    @ApiOperation(value = "退出接口", notes = "退出接口接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "退出接口 状态码"),
            @ApiResponse(code = 0, message = "退出接口 状态码"),
    })
    public Result logout(HttpServletRequest request) {
        return userService.logout(request);
    }






    /**
     * 注册接口
     *
     * @param register 包含账号密码
     * @return 返回注册是否成功
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册账号", notes = "注册账号接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "注册成功 状态码"),
            @ApiResponse(code = 0, message = "注册失败 状态码"),
    })
    public Result<String> register(@RequestBody Register register) {
        return userService.register(register);
    }


    @ApiOperation("获取验证码图片")
    @GetMapping("/getVerificationCodePhoto")
    public void getVerificationCodePhoto(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生成验证码
            String verifyCode = captchaProducer.createText();
            System.out.println(verifyCode);
//            System.out.println(verifyCode);
            //验证码字符串保存到session中
//            HttpSession session = request.getSession();
//            session.setAttribute("verifyCode", verifyCode);
//            session.setMaxInactiveInterval(60);

            //设置获取key值
            String key = getCaptchaKey(request);
            //存到redis中
            String lowerCaseCode = verifyCode.toLowerCase();
            // 将验证码加密
            String originCode = lowerCaseCode+key+ Code.getSignatureSecret();
            String individualVerifyCode = Md5Util.md5Encode(originCode, "utf-8");

            redisUtil.set(individualVerifyCode, lowerCaseCode, 1000*60*60);

            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            //设置写出图片的格式
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();

    }
    /**
     * 获取缓存的key
     * @param request
     * @return
     */
    public static String getCaptchaKey(HttpServletRequest request){
        // 获取用户ip地址
        String ip = IPConfig.getIpAddr(request);
        // 获取浏览器请求头
        String userAgent = request.getHeader("User-Agent");
        String key = "user-service:captcha:"+IPConfig.MD5(ip+userAgent);
        return key;
    }


}
