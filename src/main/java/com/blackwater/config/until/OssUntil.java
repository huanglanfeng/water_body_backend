package com.blackwater.config.until;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class OssUntil {
    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    String endpoint = ConstantPropertiesUtils.END_POINT;
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
    String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
    // 填写Bucket名称，例如examplebucket。
    private String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

    // 创建OSSClient实例。
    public OSS creatOssClient(){
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }


    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    @Cacheable(value = "code",key = "#tele")
    public String getCacheCode(String tele){
        return null;
    }
}
