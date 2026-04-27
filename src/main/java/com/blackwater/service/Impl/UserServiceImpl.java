package com.blackwater.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackwater.config.jwt.JwtUtil;
import com.blackwater.config.redis.RedisUtil;
import com.blackwater.config.until.*;
import com.blackwater.dao.UserDao;
import com.blackwater.entity.User.*;
import com.blackwater.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.bson.Document;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.blackwater.controller.LoginController.getCaptchaKey;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileUtil fileUntil;
    @Autowired
    private RedisUtil redisUtil;
//    @Autowired
//    private MongoTemplate mongoTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ConditionQueryUtil conditionQueryUtil;

    /**
     * 登录接口
     *
     * @param login 账号和密码
     * @return 返回账号和密码
     */
    @Override
//    @CachePut(value = "Account", key = "#Account")
    public Result login(HttpServletRequest httpServletRequest, Login login) {

//        String verifyCode = httpServletRequest.getSession().getAttribute("verifyCode").toString();
        //获取key值
        String key = getCaptchaKey(httpServletRequest);
        //从redis中获取verifyCode
        //加密账号
        String originCode = login.getLogin_code() + key + Code.getSignatureSecret();
        String individualVerifyCode = Md5Util.md5Encode(originCode, "utf-8");
        String verifyCode = null;
        try {
            verifyCode = (String) redisUtil.get(individualVerifyCode);
        } catch (Exception e) {
            log.warn("Redis获取验证码异常: {}", e.getMessage());
        }
        //用户输入和redis获取的验证码进行验证
        if (verifyCode == null || !login.getLogin_code().equalsIgnoreCase(verifyCode)) {
            return new Result(Code.SELECT_ERR, "验证码已过期或错误，请刷新重试");
        }

        log.info("传入的account:{},password:{}", login.getLogin_account(), login.getLogin_password());

//        HashMap<String, String> map = new HashMap<>();
        User userByAccount = userDao.findUserByAccount(login.getLogin_account());
        if (userByAccount == null) {
            log.info("账户不存在");
            return new Result<>(Code.SELECT_ERR, "账户不存在");
        } else if (!userByAccount.getPassword().equals(login.getLogin_password())) {
            log.info("密码错误");
            return new Result<>(Code.SELECT_ERR, "密码错误");
        }

        //account存入session中
//        httpServletRequest.getSession().setAttribute("loginAccount",login.getLogin_account());
//        redisUtil.set("loginAccount", login.getLogin_account());
        //查询用户信息
        User userInfo = userDao.findUserByAccount(login.getLogin_account());
        //account存入redis中
        String originAccount = key + Code.getSignatureSecret();
        String redisAccount = Md5Util.md5Encode(originAccount, "utf-8");
        redisUtil.set(redisAccount, login.getLogin_account());
        //登录成功删除验证码
//        redisUtil.del(individualVerifyCode);
        //用户信息存入缓存
//        mongoTemplate.save(userInfo);
        //生成token
        JwtUtil jwtUtil = new JwtUtil();
        HashMap<String, Object> chaim = new HashMap<>();
        chaim.put("loginAccount", login.getLogin_account());
        String jwtToken = jwtUtil.encode(login.getLogin_account(), 120000000, chaim);

        return new Result<>(Code.SELECT_OK, jwtToken, "登录成功", userInfo.getName(), userInfo.getRole());

    }

    /**
     * 退出登录接口
     *
     * @return
     */
    @Override
    public Result logout(HttpServletRequest httpServletRequest) {
//        String token = request.getHeader("X-Access-Token");
//        if (token == null) {
        //获取key值
        String key = getCaptchaKey(httpServletRequest);
        String originAccount = key + Code.getSignatureSecret();
        String redisAccount = Md5Util.md5Encode(originAccount, "utf-8");
        String account = (String)redisUtil.get(redisAccount);
//        redisUtil.del(redisAccount);

//        //删除用户信息
//        Query query = new Query(Criteria.where("account").is(account));
//        mongoTemplate.remove(query, User.class);

        return new Result(Code.OK, "退出成功");
//        }
//        return new Result(Code.OK, "退出成功");
    }


    /**
     * 注册接口
     *
     * @param register 账号、密码、邮箱、性别
     * @return 返回注册是否成功
     */
    @Override
    public Result<String> register(Register register) {

        try {
            User userByAccount = userDao.findUserByAccount(register.getAccount());
            if (userByAccount != null) {
                log.info("传入注册的account:{}", register.getAccount());
                log.warn("账号已存在");
                return new Result<String>(Code.ERR, "账号已存在", null);
            } else {
                UUID uuid = UUID.randomUUID();
                String s = String.valueOf(uuid).split("-")[0] + "用户";
                User user = new User();
                user.setAccount(register.getAccount());
                user.setPassword(register.getPassword());
//                user.setMail(register.getMail());
//                user.setGender(register.getGender());
                user.setName(s);
                //设置默认信息
                String r = "用户";
                user.setRole(r);
                String p = "https://storage-public.zhaopin.cn/user/avatar/1583854162228112270/62a5a5c0-ec59-47b4-8e37-83a9387d3ba8.jpg";
                user.setPhoto(p);
                userDao.register(user);
                log.info("注册成功");
                return new Result<>(Code.OK, "注册成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出现异常");
            return new Result<>(Code.EXCEPTION, "系统出现异常");
        }
    }


    /**
     * 检验账号接口
     *
     * @param account 账号
     * @return 返回校验结果
     */
    @Override
    public Result<String> checkAccount(String account) {
        log.info("传入校验的account：{}", account);
        int length = account.length();
        log.info("account长度:" + length);
        if (length == 11) {
            log.info("账号格式正确");
            return new Result<String>(Code.OK, "账号格式正确", null);
        } else {
            log.info("账号格式错误");
            return new Result<String>(Code.ERR, "账号格式错误", null);
        }
    }


    /**
     * 根据id删除用户
     *
     * @param
     * @return 返回是否删除成功
     */
    @Override
    public Result<String> deleteById(User user) {
        int id = user.getId();
        log.info("传入需要删除的id:{}", id);
        try {
            int deleteById = userDao.delById(id);
            if (deleteById == 0) {
                log.info("删除失败");
                return new Result<String>(Code.ERR, "删除失败", null);
            } else {
                log.info("删除成功");
                return new Result<String>(Code.OK, "删除成功", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出现异常");
            return new Result<>(Code.EXCEPTION, "系统出现异常");
        }

    }


    /**
     * 查询所有用户信息
     *
     * @return 所有用户信息
     */
    @Override
    public Result<List<User>> selectAll() {
//        log.info("调用查全接口");
        try {
            List<User> users = userDao.selectAll();
//            log.info("查询成功");
            return new Result<>(Code.OK, "查询成功", users);
        } catch (Exception e) {
            log.error("系统出现异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统出现异常", null);
        }
    }

    /**
     * 生成验证码
     *
     * @return 返回验证码
     */
    @Override
    public Result<String> generateCode() {
        log.info("调用生成验证码接口");
        try {
            //生成验证码
            StringBuilder s = new StringBuilder();
            Random r = new Random();
            for (int i = 0; i < 6; i++) {
                s.append(r.nextInt(9));
            }

            log.info(String.valueOf(s));
            //将生成的验证码存入到session中
            HttpSession session = request.getSession();
            session.setAttribute("generateCode", String.valueOf(s));

            return new Result<String>(Code.OK, "成功生成验证码", String.valueOf(s));
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常");
        }
    }


    /**
     * 找回密码接口
     *
     * @param retrieve
     * @return
     */
    @Override
    public Result<String> retrievePassword(HttpServletRequest request, Retrieve retrieve) {
        log.info("传入的找回信息" + retrieve);
        //从session域中取出code
        try {
            HttpSession session = request.getSession();
            String checkCode = (String) session.getAttribute("retrieveEmailCode");
            if (retrieve.getRetrieve_code().equalsIgnoreCase(checkCode)) {
                log.info("校验成功");
                userDao.updatePasswordByAccount(retrieve.getRetrieve_account(), retrieve.getRetrieve_password());
                return new Result<>(Code.OK, "校验成功");
            } else {
                log.error("校验失败");
                return new Result<>(Code.ERR, "校验失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改信息接口
     *
     * @param user
     * @return
     */
    @Override
    public Result<String> updateAllById(User user , HttpServletRequest httpServletRequest) {
        log.info("传入需要修改的用户信息:" + user);
        System.out.println(user.getAccount());
        System.out.println(user.getName());
        if(user.getAccount() == null){

            //TODO 这里是修改信息依据死数据
            //从redis中获取用户账号
            String key = getCaptchaKey(httpServletRequest);
            String originAccount = key + Code.getSignatureSecret();
            String redisAccount = Md5Util.md5Encode(originAccount, "utf-8");
            String loginAccount = (String) redisUtil.get(redisAccount);
            System.out.println(loginAccount);
            //修改
            userDao.updateNameByAccount(loginAccount, user.getName());
            return new Result<>(Code.OK, "修改成功", null);
        }
        try {
            userDao.updateByAccount(user.getAccount(), user.getPassword(),
                    user.getGender(), user.getMail(), user.getId(), user.getRole());
            log.info("修改成功");
            return new Result<>(Code.OK, "修改成功", null);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }

    }

    /**
     * 分页查询接口
     *
     * @param current 当前页码
     * @param size    每页的数据
     * @return
     */
    @Override
    public Result<List<User>> selectByPage(int current, int size) {
        log.info("传入的current:{},size:{}", current, size);
        try {
            IPage<User> page = new Page<>(current, size);
            userDao.selectPage(page, null);
            log.info("分页查询成功");
            return new Result<List<User>>(Code.OK, "分页查询成功", page.getRecords());
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 查询公告接口
     *
     * @return
     */
    @Override
    public Result<List<Notice>> selectNoticeData() {

        try {
            List<Notice> notices = userDao.selectNoticeData();
            log.info("查询公告成功");
            return new Result<List<Notice>>(Code.OK, "查询公告成功", notices);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }

    }

    /**
     * 添加公告接口
     *
     * @return
     */
    @Override
    public Result<String> addNotice(Notice notice) {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notice.setRelease_time(String.valueOf(format.format(date)));
        try {
            userDao.addNotice(notice);
            log.info("添加公告成功");
            return new Result<String>(Code.OK, "添加公告成功", null);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }


    /**
     * 根据id删除公告
     *
     * @param id
     * @return
     */
    @Override
    public Result<String> deleteNoticeById(int id) {
        log.info("传入的id:" + id);
        try {
            int i = userDao.deleteNoticeById(id);
            log.info("删除公告成功");
            return new Result<>(Code.OK, "删除公告成功");
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 查询反馈
     *
     * @return
     */
    @Override
    public Result<List<Feedback>> selectFeedback() {
        try {
            List<Feedback> feedbacks = userDao.selectFeedback();
            log.info("查询反馈成功");
            return new Result<>(Code.OK, "查询反馈成功", feedbacks);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 删除反馈
     *
     * @param id
     * @return
     */
    @Override
    public Result<String> deleteFeedback(int id) {
        userDao.deleteFeedback(id);
        log.info("删除反馈成功");
        return new Result<>(Code.OK, "删除反馈成功");
    }

    /**
     * 添加反馈
     *
     * @return
     */
    @Override
    public Result<String> addFeedback(RegisterFeedback registerFeedback) {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        registerFeedback.setTime(String.valueOf(format.format(date)));
        try {
            userDao.addFeedback(registerFeedback);
            log.info("添加反馈成功");
            return new Result<>(Code.OK, "添加反馈成功");
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }


    }

    /**
     * 修改头像
     *
     * @param
     * @return
     */
    @Override
    public Result<String> updatePhoto(HttpServletRequest httpServletRequest, MultipartFile file) {

        //从token获取用户账号
//        String loginAccount = JwtUtil.getAccountByToken(httpServletRequest);
        //获取key值
        String key = getCaptchaKey(httpServletRequest);
        //从redis中获取用户账号
        String originAccount = key + Code.getSignatureSecret();
        String redisAccount = Md5Util.md5Encode(originAccount, "utf-8");
        String loginAccount = (String) redisUtil.get(redisAccount);
        if (loginAccount == null) {
            return new Result<>(Code.ERR, "缓存没有此account");
        }

        User userByAccount = userDao.findUserByAccount(loginAccount);
        if (userByAccount == null) {
            return new Result<>(Code.ERR, "不存在改用户");
        }
        try {
            //上传图片
            String url = fileUntil.fileSave(file);
            userDao.updatePhotoByAccount(url, loginAccount);
            return new Result<>(Code.OK, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统出错");
        }
    }

    /**
     * 获取用户信息，据缓存中的account
     *
     * @return
     */
    @Override
    public Result<User> getUserByAccount(HttpServletRequest httpServletRequest) {
        //从token获取用户账号
//        String loginAccount = JwtUtil.getAccountByToken(httpServletRequest);
//        System.out.println(loginAccount);
        //从redis中获取用户账号
        String key = getCaptchaKey(httpServletRequest);
        String originAccount = key + Code.getSignatureSecret();
        String redisAccount = Md5Util.md5Encode(originAccount, "utf-8");
        String loginAccount = (String) redisUtil.get(redisAccount);
        //查询信息
        User userByAccount = userDao.findUserByAccount(loginAccount);
        return new Result<>(Code.OK, userByAccount);

    }

    /**
     * 管理信息
     *
     * @return
     */
    @Override
    public Result<List<Admin>> getAllAdmin() {
        List<Admin> allAdmin = userDao.getAllAdmin();
        return new Result<List<Admin>>(Code.OK, "查询成功", allAdmin);
    }

    /**
     * 查询用户（条件）
     *
     * @param condition
     * @return
     */
    @Override
    public Result<HashSet<User>> getUserByCondition(String condition) {
        List<String> allCondition = conditionQueryUtil.getAllString(condition);
        HashSet<User> returnUser = new HashSet<User>();
        for(String s : allCondition){
            List<User> userByCondition = userDao.getUserByCondition(s);
            for(User user: userByCondition){
                returnUser.add(user);
            }
        }
        return new Result<>(Code.OK,"查询成功",returnUser);
    }


}



