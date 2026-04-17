package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class Login {
    @ApiModelProperty("用户名")
    private String login_account;

    @ApiModelProperty("密码")
    private String login_password;

    @ApiModelProperty("验证码")
    private String login_code;

}
