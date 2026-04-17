package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Register {


    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;





}
