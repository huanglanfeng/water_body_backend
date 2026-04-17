package com.blackwater.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackwater.entity.User.*;
import com.blackwater.config.until.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@Service
public interface UserService extends IService<User> {


    /**
     * 登录接口
     *
     * @param account 账号和密码
     * @return 返回账号和密码
     */
    Result login(HttpServletRequest httpServletRequest, Login account);

    /**
     * 退出登录接口
     *
     * @return
     */

    Result logout(HttpServletRequest httpServletRequest);


    /**
     * 注册接口
     *
     * @param register 账号和密码
     * @return 返回注册是否成功
     */

    Result<String> register(Register register);


    /**
     * 检验账号接口
     *
     * @param account 账号
     * @return 返回校验结果
     */
    Result<String> checkAccount(String account);

    /**
     * 根据id删除用户
     *
     * @param id 序号
     * @return 返回是否删除成功
     */
    Result<String> deleteById(User user);


    /**
     * 查询所有用户信息
     *
     * @return 所有用户信息
     */
    Result<List<User>> selectAll();

    /**
     * 生成验证码
     *
     * @return 返回验证码
     */
    Result<String> generateCode();

    /**
     * 找回密码接口
     *
     * @param retrieve
     * @return
     */
    Result<String> retrievePassword(HttpServletRequest request, Retrieve retrieve);

    /**
     * 修改信息接口
     *
     * @param user
     * @return
     */
    Result<String> updateAllById(User user , HttpServletRequest httpServletRequest);

    /**
     * 分页查询接口
     *
     * @param current 当前页码
     * @param size    每页的数据
     * @return
     */
    Result<List<User>> selectByPage(int current, int size);

    /**
     * 查询通知接口
     *
     * @return
     */
    Result<List<Notice>> selectNoticeData();

    /**
     * 添加公告接口
     *
     * @return
     */
    Result<String> addNotice(Notice notice);

    /**
     * 根据id删除公告
     *
     * @param id
     * @return
     */
    Result<String> deleteNoticeById(int id);

    /**
     * 查询反馈
     *
     * @return
     */
    Result<List<Feedback>> selectFeedback();

    /**
     * 删除反馈
     *
     * @param id
     * @return
     */
    Result<String> deleteFeedback(int id);

    /**
     * 添加反馈
     *
     * @return
     */

    Result<String> addFeedback(RegisterFeedback registerFeedback);

    /**
     * 修改头像
     *
     * @return
     */

    Result<String> updatePhoto(HttpServletRequest request,MultipartFile file);


    /**
     * 获取用户信息，据缓存中的account
     * @return
     */
    Result<User>  getUserByAccount(HttpServletRequest request);


    /**
     * 管理信息
     * @return
     */
    Result<List<Admin>> getAllAdmin();

    /**
     * 查询用户（条件）
     * @param condition
     * @return
     */
    Result<HashSet<User>>  getUserByCondition(String condition);
}
