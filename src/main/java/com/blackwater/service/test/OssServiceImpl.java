package com.blackwater.service.test;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.blackwater.config.redis.RedisUtil;
import com.blackwater.config.until.*;
import com.blackwater.dao.UserDao;
import com.blackwater.entity.User.User;
import com.blackwater.entity.test.Test1;
import com.blackwater.entity.test.Test2;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author An
 * @description
 * @date 2022/6/22 1:26
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Autowired
    private DefaultKaptcha captchaProducer;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OssUntil codeUtil;


    ApplicationHome applicationHome = new ApplicationHome(this.getClass());
    String Path = applicationHome.getDir().getParentFile()
            .getParentFile().getAbsolutePath() + "\\src\\main\\resources";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private GetContentType getContentType;
    @Autowired
    private OssUntil ossUntil;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private UserDao userDao;

    @Override
    public Result uploadImages(Test1 register, MultipartFile file) {

        try {
            User userByAccount = userDao.findUserByAccount(register.getAccount());
            if (userByAccount != null) {
                log.info("传入注册的account:{}", register.getAccount());
                log.warn("账号已存在");
                return new Result<String>(Code.ERR, "账号已存在", null);
            } else {
                //将图片保存到OSS
                String url = fileUtil.fileSave(file);
                //将图片url保存到数据库
                register.setImage(url);
                UUID uuid = UUID.randomUUID();
                String s = String.valueOf(uuid).split("-")[0] + "用户";
                User user = new User();
                user.setAccount(register.getAccount());
                user.setPassword(register.getPassword());
//                user.setMail(register.getMail());
//                user.setGender(register.getGender());
                user.setName(s);
                String r = "用户";
                user.setRole(r);
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
     * 删除图片
     *
     * @param fileUrl 图片路径
     * @return
     */
    @Override
    public boolean deleteImages(String fileUrl) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String imgFile = fileUrl.replace("https://bucket-ans.oss-cn-hangzhou.aliyuncs.com/", "");
        String fileName = imgFile.substring(0, imgFile.indexOf("?"));

        // 根据BucketName,objectName删除文件
        ossClient.deleteObject(bucketName, fileName);
        ossClient.shutdown();
        return true;
    }

    // 实现图片的预览功能
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }


    @Override
    @CachePut(value = "code", key = "#tele")
    public String getVerificationCode(String tele) {
        String verifyCode = captchaProducer.createText();
        return verifyCode;
    }

    @Override
    public Boolean checkVerificationCode(Test2 test) {
        String cacheCode = codeUtil.getCacheCode(test.getTele());
        return test.getCode().equals(cacheCode);
    }

    @Override
    public String uploadPre(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtils.END_POINT, ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
        String url = "";
        //得到初始文件名originalFileName
        String filename = file.getOriginalFilename();
        File file1 = new File(Path, filename);
        file.transferTo(file1);
        log.info(file1.getAbsolutePath());
        Integer size = Math.toIntExact((file.getSize()));
        // oss中的文件夹名
        String objectName = sdf.format(new Date()) + "/" + filename;
        String type = getContentType.getContentType(filename.substring(filename.lastIndexOf(".")));
        // 创建上传文件的元信息，可以通过文件元信息设置HTTP header(设置了才能通过返回的链接直接访问)。
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(type);
        objectMetadata.setContentDisposition("inline");
        //缓存文件上传至OSS
        InputStream inputStream = new FileInputStream(file1);
        ossClient.putObject(ConstantPropertiesUtils.BUCKET_NAME, objectName, inputStream, objectMetadata);

        log.info("*******运行到上传文件");// 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 360000 * 3600 * 1000 * 24 * 2000 * 1000);
        // 设置样式，样式中包含文档预览参数。
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(ConstantPropertiesUtils.BUCKET_NAME, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        url = signedUrl.toString();
        String downloadUrl = url.toString() + "&download=" + URLEncoder.encode(objectName, "UTF-8");
        log.info(downloadUrl);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        /*
         * 设计存储到数据库的名字fileName
         * System.currentTimeMillis()获取文件存储到库时的时间
         * 获取时间加上文件名
         */
        ossClient.shutdown();

        file1.delete();

        return url;
    }


}