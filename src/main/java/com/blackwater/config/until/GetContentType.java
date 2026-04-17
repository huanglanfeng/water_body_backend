package com.blackwater.config.until;

import org.springframework.stereotype.Component;


/**
 * @program: git-zhihui-backen
 * @description:
 * @author: 张浩
 * @create: 2023-06-19 10:16
 **/
@Component
public class GetContentType {


    public String getContentType(String filenameExtension){
        switch (filenameExtension){
            case ".pdf":
                return "application/pdf";
            case ".bmp":
                return "image/bmp";
            case ".gif":
                return "image/gif";
            case ".jpeg":
            case ".jpg" :
            case ".png":
                return "image/jpg";
            case ".html":
                return "text/html";
            case ".txt":
                return "text/plain";
            case ".vsd":
                return "application/vnd.visio";
            case ".pptx":
            case ".ppt":
                return "application/vnd.ms-powerpoint";
            case ".doc":
                return "application/msword";
            case ".docx":
                return "application/msword";
                //return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case ".xml":
                return "text/xml";
            case ".dot":
                return "application/msword";
            case ".dotx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
            case ".docm":
                return "application/vnd.ms-word.document.macroEnabled.12";
            case ".dotm":
                return "application/vnd.ms-word.template.macroEnabled.12";
            case ".xls":
            case ".xlt":
                return "application/vnd.ms-excel";
            case ".xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case ".xltx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
            case ".xlsm":
                return "application/vnd.ms-excel.sheet.macroEnabled.12";
            case ".xltm":
                return "application/vnd.ms-excel.template.macroEnabled.12";
            case ".xlam":
                return "application/vnd.ms-excel.addin.macroEnabled.12";
            case ".pot":
                return "application/vnd.ms-powerpoint";
            case ".pps":
                return "application/vnd.ms-powerpoint";
            case ".ppa":
                return "application/vnd.ms-powerpoint";
            case ".potx":
                return "application/vnd.openxmlformats-officedocument.presentationml.template";
            case ".ppsx":
                return "application/vnd.openxmlformats-officedocument.presentationml.slideshow";
            case ".ppam":
                return "application/vnd.ms-powerpoint.addin.macroEnabled.12";
            case ".pptm":
                return "application/vnd.ms-powerpoint.presentation.macroEnabled.12";
            case ".potm":
                return "application/vnd.ms-powerpoint.template.macroEnabled.12";
            case ".ppsm":
                return "application/vnd.ms-powerpoint.slideshow.macroEnabled.12";
        }
        return "image/jpg";

    }
}







