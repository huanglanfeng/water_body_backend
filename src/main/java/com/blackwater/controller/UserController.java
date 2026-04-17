package com.blackwater.controller;

import com.blackwater.config.redis.RedisUtil;
import com.blackwater.entity.User.*;
import com.blackwater.config.until.Result;
import com.blackwater.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;


@RestController
@Api(tags = "用户")
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    private final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    /**
     * 校验账号接口
     *
     * @param account 账号 (11位)
     * @return 返回校验结果
     */

    @PostMapping("/check/account")
    @ApiOperation(value = "校验账号", notes = "校验账号接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "校验账号 成功状态码"),
            @ApiResponse(code = 0, message = "校验账号 失败状态码")
    })
    public Result<String> checkAccount(String account) {
        return userService.checkAccount(account);
    }

    /**
     * 根据id删除用户
     *
     * @return 返回是否删除成功
     */

    @PostMapping("/delete/id")
    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "根据id删除用户 成功状态码"),
            @ApiResponse(code = 0, message = "根据id删除用户 失败状态码")
    })
    public Result<String> deleteById(int id, @RequestBody User user) {
        System.out.println(id);
        return userService.deleteById(user);
    }


    /**
     * 查询所有用户信息
     *
     * @return 所有用户信息
     */
    @PostMapping("/select/all")
    @ApiOperation(value = "查询所有用户信息", notes = "查询所有用户信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询所有用户信息 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<User>> selectAll() {
        return userService.selectAll();
    }

    /**
     * 生成验证码
     *
     * @return 返回验证码
     */
    @PostMapping("/generate/code")
    @ApiOperation(value = "生成验证码", notes = "生成验证码 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "生成验证码 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<String> generateCode() {
        return userService.generateCode();
    }


    /**
     * 找回密码
     *
     * @param retrieve
     * @return
     */
    @PostMapping("/retrieve/password")
    @ApiOperation(value = "找回密码", notes = "找回密码 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "找回密码 成功状态码"),
            @ApiResponse(code = 0, message = "找回密码 失败状态码")
    })
    public Result<String> retrievePassword(HttpServletRequest request, @RequestBody Retrieve retrieve) {
        return userService.retrievePassword(request, retrieve);
    }

    /**
     * 修改信息
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息", notes = "修改信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "修改信息 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<String> updateByAccount(@RequestBody User user ,HttpServletRequest httpServletRequest) {
        return userService.updateAllById(user,httpServletRequest);
    }


    /**
     * 分页查询
     *
     * @param current 当前页码
     * @param size    每页的数据
     * @return
     */
    @PostMapping("/select/page")
    @ApiOperation(value = "分页查询", notes = "分页查询 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "分页查询 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<User>> selectByPage(int current, int size) {
        return userService.selectByPage(current, size);
    }

    /**
     * 查询公告
     *
     * @return
     */
    @PostMapping("/select/notice/data")
    @ApiOperation(value = "查询公告", notes = "查询公告 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询公告 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Notice>> selectNoticeData() {
        return userService.selectNoticeData();
    }

    /**
     * 添加公告
     *
     * @param notice
     * @return
     */
    @PostMapping("/add/notice")
    @ApiOperation(value = "添加公告", notes = "添加公告 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "添加公告 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<String> addNotice(@RequestBody Notice notice) {
        return userService.addNotice(notice);
    }


    /**
     * 根据id删除公告
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/notice/by/id")
    @ApiOperation(value = "删除公告", notes = "删除公告 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "删除公告 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<String> deleteNoticeById(int id) {
        return userService.deleteNoticeById(id);
    }

    /**
     * 查询反馈
     *
     * @return
     */
    @PostMapping("/select/feedback")
    @ApiOperation(value = "查询反馈", notes = "查询反馈 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询反馈 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Feedback>> selectFeedback() {
        return userService.selectFeedback();
    }

    /**
     * 删除反馈
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/feedback")
    @ApiOperation(value = "删除反馈", notes = "删除反馈 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "删除反馈 成功状态码"),
    })
    public Result<String> deleteFeedback(@RequestParam("id") int id) {
        return userService.deleteFeedback(id);
    }

    /**
     * 添加反馈
     *
     * @return
     */
    @PostMapping("/add/feedback")
    @ApiOperation(value = "添加反馈", notes = "添加反馈 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "添加反馈 成功状态码"),
    })
    public Result<String> addFeedback(@RequestBody RegisterFeedback registerFeedback) {
        return userService.addFeedback(registerFeedback);
    }


    /**
     * 修改头像
     *
     * @return
     */
    @PostMapping("/update/photo")
    @ApiOperation(value = "修改头像", notes = "修改头像 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "修改头像 成功状态码"),
    })
    public Result<String> updatePhoto(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) {
        return userService.updatePhoto(request, file);
    }

    /**
     * 获取用户信息，据缓存中的account
     *
     * @return
     */
    @PostMapping("/get/user/by/account")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "获取用户信息 成功状态码"),
    })
    public Result<User> getUserByAccount(HttpServletRequest request) {
        return userService.getUserByAccount(request);
    }

    /**
     * 管理信息
     *
     * @return
     */
    @GetMapping("/get/all/admin")
    @ApiOperation(value = "管理信息", notes = "管理信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "管理信息 成功状态码"),
    })
    public Result<List<Admin>> getAllAdmin( ) {
        return userService.getAllAdmin();
    }


    /**
     * 查询用户（条件）
     *
     * @return
     */
    @GetMapping("/get/user/by/condition")
    @ApiOperation(value = "条件查询", notes = "条件查询 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "条件查询 成功状态码"),
    })
    public Result<HashSet<User>> getUserByCondition(String condition ) {
        return userService.getUserByCondition(condition);
    }

}
