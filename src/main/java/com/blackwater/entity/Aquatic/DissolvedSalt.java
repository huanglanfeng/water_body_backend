package com.blackwater.entity.Aquatic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DissolvedSalt {
    @ApiModelProperty("地区")
    private String siteId;
    @ApiModelProperty("硫酸盐")
    private double sulfate;
    @ApiModelProperty("硝酸盐")
    private double nitrate;
    @ApiModelProperty("高猛酸盐")
    private double permanganate;
    @ApiModelProperty("电导率")
    private double conductivity;
    @ApiModelProperty("检测时间")
    private String detectTime;
}
