package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Admin {

    @ApiModelProperty("序号")
    private int id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("权限")
    private String role;
}
