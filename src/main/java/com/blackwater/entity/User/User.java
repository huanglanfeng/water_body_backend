package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {


    @ApiModelProperty("序号")
    private Integer id;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("邮箱")
    private String mail;
    @ApiModelProperty("图片")
    private String photo;
    @ApiModelProperty("权限")
    private String role;


}
