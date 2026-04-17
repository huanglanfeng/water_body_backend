package com.blackwater.entity.Heavy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Ph {
    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("酸碱度")
    private double ph;
    @ApiModelProperty("异常(偏)")
    private String exception;
    @ApiModelProperty("异常地区")
    private String exceptionArea;
    @ApiModelProperty("预警时间")
    private String warningTime;
}
