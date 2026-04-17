package com.blackwater.entity.test;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Test2 {
    @ApiModelProperty("账号")
    private String tele;
    @ApiModelProperty("密码")
    private String code;
}
