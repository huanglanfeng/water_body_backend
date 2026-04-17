package com.blackwater.entity.Aquatic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Radiation {
    @ApiModelProperty("地区")
    private String exceptionArea;
    @ApiModelProperty("射线1")
    private double ray1;
    @ApiModelProperty("射线2")
    private double ray2;
    @ApiModelProperty("钍")
    private double thorium;
    @ApiModelProperty("镭")
    private double radium;
    @ApiModelProperty("铀")
    private double uranium;
    @ApiModelProperty("检测时间")
    private String detectTime;
}
