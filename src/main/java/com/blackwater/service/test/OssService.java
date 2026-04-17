package com.blackwater.service.test;
import com.blackwater.config.until.Result;
import com.blackwater.entity.User.Register;
import com.blackwater.entity.test.Test1;
import com.blackwater.entity.test.Test2;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author An
 * @description
 * @date 2022/6/22 1:25
 */
public interface OssService {
    /**
     * 上传图片
     * @param file
     * @return
     */
    Result uploadImages(Test1 register, MultipartFile file);



    /**
     * 删除图片
     * @param fileUrl
     * @return
     */
    boolean deleteImages(String fileUrl);


    String getVerificationCode(String tele) ;


    Boolean checkVerificationCode(Test2 tele);

    /**
     * 预览图片
     * @param file
     * @return
     * @throws IOException
     */
    String uploadPre(MultipartFile file) throws IOException;





}
