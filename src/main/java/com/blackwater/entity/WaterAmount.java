package com.blackwater.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WaterAmount {

    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("地区")
    private String site;
    @ApiModelProperty("水量")
    private double amount;
}
