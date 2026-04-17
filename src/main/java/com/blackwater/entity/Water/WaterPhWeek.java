package com.blackwater.entity.Water;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WaterPhWeek {
    @ApiModelProperty("p1")
    private double p1;
    @ApiModelProperty("p2")
    private double p2;
    @ApiModelProperty("p3")
    private double p3;
    @ApiModelProperty("p4")
    private double p4;
    @ApiModelProperty("p5")
    private double p5;
    @ApiModelProperty("p6")
    private double p6;
    @ApiModelProperty("p7")
    private double p7;
}
