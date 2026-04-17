package com.blackwater.controller.test;


//import com.an.notepad.common.Result;
//import com.an.notepad.service.OssService;

import com.blackwater.config.until.FileUtil;
import com.blackwater.config.until.Result;
import com.blackwater.entity.test.Test1;
import com.blackwater.entity.test.Test2;
import com.blackwater.service.test.OssService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.cache.annotation.CachePut;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author An
 * @description
 * @date 2022/6/22 1:24
 */
@RestController
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    @Autowired
    private FileUtil fileUtil;
    ApplicationHome applicationHome = new ApplicationHome(this.getClass());
    String Path = applicationHome.getDir().getParentFile()
            .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\image";

    @ApiOperation(value = "上传图片")
    @RequiresAuthentication
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        // 获取上传头像的文件 MultipartFile
        // 返回上传的oss路径
//        System.out.println(file);
//        String url = String.valueOf(ossService.uploadImages(register, file));
        System.out.println("调用了upload");
        return ossService.uploadPre(file);
//        return new Result<String>(url);
    }

    @ApiOperation(value = "删除图片")
    @RequiresAuthentication
    @PostMapping("/delete")
    public Result deleteImages(String fileUrl) {
        boolean flag = ossService.deleteImages(fileUrl);
        if (flag) {
            return new Result("删除成功");
        }
        return new Result("删除失败");
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getVerificationCode")

    public String getVerificationCode(String tele) {
        return ossService.getVerificationCode(tele);
    }

    @ApiOperation("校验验证码")
    @GetMapping("/checkVerificationCode")
    public Boolean checkVerificationCode(Test2 test) {
        return ossService.checkVerificationCode(test);
    }


    @ApiOperation("本地文件上传测试")
    @PostMapping("/upload/location")
    public String uploadImages(MultipartFile file) throws IOException {
        //TODO 处理预览图片
        System.out.println("调用uploadImages");
        //file校验
        if (file == null) {
            return "空文件";
        }
        //file重命名
        String originalFilename = file.getOriginalFilename(); //原来图片名
        String ext = '.' + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
        //上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "\\src\\main\\resources\\static\\images\\";
        String path = pre + fileName;
        File file1 = new File(path);
        file.transferTo(file1);

//        //设置返回类型
//        response.setContentType("image/jpg");
//        //将图片保存到response中
//        FileCopyUtils.copy(new FileInputStream(path),response.getOutputStream());
        return path;
    }

    @ApiOperation("添加预览文件")
    @ApiImplicitParam(name = "file", value = "文件")
    @PostMapping(value = "/upload/pre")
    public ResponseEntity<Object> uploadPre(@RequestParam("file") MultipartFile file) {
//        return ossService.uploadPre(file);
//        System.out.println("调用了Pre");
//        return fileUtil.fileSave(file);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8012/fileUpload";
        HttpEntity<MultipartFile> request = new HttpEntity<>(file);
        String s = restTemplate.postForObject(url, request, String.class);
        return ResponseEntity.ok(s);

    }


}
