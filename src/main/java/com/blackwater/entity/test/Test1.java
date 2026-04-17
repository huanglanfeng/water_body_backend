package com.blackwater.entity.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Test1 {
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("图片")
    private String image;
}
