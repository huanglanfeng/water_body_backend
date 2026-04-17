package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Retrieve {
    @ApiModelProperty("账号")
    private String retrieve_account;
    @ApiModelProperty("验证码")
    private String retrieve_code;
    @ApiModelProperty("密码")
    private String retrieve_password;

}
