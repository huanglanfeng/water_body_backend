package com.blackwater.entity.phone;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Fault {

    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("编号")
    private int number;
    @ApiModelProperty("描述")
    private String description;


}
