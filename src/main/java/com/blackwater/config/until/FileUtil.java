package com.blackwater.config.until;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class FileUtil {

    public static String ALIYUN_OSS_ENDPOINT = System.getenv().getOrDefault("OSS_ENDPOINT", "oss-cn-beijing.aliyuncs.com");
    public static String ALIYUN_OSS_ACCESSKEYID = System.getenv().getOrDefault("OSS_ACCESS_KEY_ID", "");
    public static String ALIYUN_OSS_ACCESSKEYSECRET = System.getenv().getOrDefault("OSS_ACCESS_KEY_SECRET", "");
    public static String ALIYUN_OSS_BUCKETNAME = System.getenv().getOrDefault("OSS_BUCKET_NAME", "waterbody");

    ApplicationHome applicationHome = new ApplicationHome(this.getClass());
    String Path = applicationHome.getDir().getParentFile()
            .getParentFile().getAbsolutePath() + "\\src\\main\\resources";

    public String fileSave(MultipartFile file) {
        log.info("无误");
        try {
            OSS ossClient = new OSSClientBuilder().build(ALIYUN_OSS_ENDPOINT, ALIYUN_OSS_ACCESSKEYID, ALIYUN_OSS_ACCESSKEYSECRET);
            log.info("运行到FileSave");
            // 得到初始图片名字originalFileName
            String originalFileName = file.getOriginalFilename();

            // 文件存储库位置
            File file1 = new File(Path, originalFileName);
            file.transferTo(file1);
            log.info(file1.getAbsolutePath());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            // oss中的文件夹名
            String FileName = sdf.format(new Date()) + "/" + originalFileName;

            // 创建上传文件的元信息，可以通过文件元信息设置HTTP header(设置了才能通过返回的链接直接访问)。
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/jpg");
            objectMetadata.setContentDisposition("inline");

            //缓存文件上传至OSS
            InputStream inputStream = Files.newInputStream(file1.toPath());
            ossClient.putObject(ALIYUN_OSS_BUCKETNAME, FileName, inputStream, objectMetadata);

            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 2000 * 1000);


            // 设置样式，样式中包含文档预览参数。
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(ALIYUN_OSS_BUCKETNAME, FileName, HttpMethod.GET);
            req.setExpiration(expiration);
            URL signedUrl = ossClient.generatePresignedUrl(req);
            log.info("无误");
            String url = signedUrl.toString();
            ossClient.shutdown();
            file1.delete();
            return url;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
