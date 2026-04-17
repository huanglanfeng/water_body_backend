package com.blackwater.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackwater.entity.User.*;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据账号和密码查询用户
     *
     * @param account  账号
     * @param password 密码
     * @return 返回User实体类
     */
    User findUser(String account, String password);

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return 返回User实体类
     */
    User findUserByAccount(String account);

    /**
     * 注册
     *
     * @param user
     */
    void register(User user);


    /**
     * 根据id删除用户
     *
     * @param id 序号
     * @return 返回删除是否成功
     */

    int delById(int id);

    /**
     * 查询所有用户信息
     *
     * @return 用户信息
     */
    List<User> selectAll();

    /**
     * 修改密码 根据账号
     *
     * @param account 账号
     * @return
     */

    int updatePasswordByAccount(String account, String password);

    /**
     * 修改信息
     *
     * @param account
     * @param password
     * @param gender
     * @param mail
     * @return
     */
    int updateByAccount(String account, String password,
                        String gender, String mail, int id,
                        String role);

    /**
     * 修改用户名
     * @param account
     * @param name
     * @return
     */
    boolean updateNameByAccount(String account ,String name);

    /**
     * 查询通知信息
     */
    List<Notice> selectNoticeData();

    /**
     * 添加公告
     *
     * @param notice
     * @return
     */
    int addNotice(Notice notice);

    /**
     * 根据id删除公告
     *
     * @param id
     * @return
     */
    int deleteNoticeById(int id);

    /**
     * 查询反馈
     *
     * @return
     */
    List<Feedback> selectFeedback();

    /**
     * 删除反馈
     *
     * @param id
     * @return
     */
    int deleteFeedback(int id);

    /**
     * 增加反馈
     *
     * @param registerFeedback
     * @return
     */

    int addFeedback(RegisterFeedback registerFeedback);

    /**
     * 修改头像
     *
     * @param url
     * @param account
     * @return
     */
    boolean updatePhotoByAccount(String url, String account);

    /**
     * 管理信息
     *
     * @return
     */
    List<Admin> getAllAdmin();

    /**
     * 条件模糊查询
     * @param condition 条件
     * @return
     */
    List<User>  getUserByCondition(String condition);

}
