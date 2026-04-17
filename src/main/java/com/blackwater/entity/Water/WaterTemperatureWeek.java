package com.blackwater.entity.Water;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WaterTemperatureWeek {

    @ApiModelProperty("t1")
    private double t1;
    @ApiModelProperty("t2")
    private double t2;
    @ApiModelProperty("t3")
    private double t3;
    @ApiModelProperty("t4")
    private double t4;
    @ApiModelProperty("t5")
    private double t5;
    @ApiModelProperty("t6")
    private double t6;
    @ApiModelProperty("t7")
    private double t7;

}
