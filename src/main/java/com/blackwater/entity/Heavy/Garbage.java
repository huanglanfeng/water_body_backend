package com.blackwater.entity.Heavy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Garbage {

    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("设备编号")
    private int deviceId;
    @ApiModelProperty("异常")
    private String exception;
    @ApiModelProperty("异常地区")
    private String exceptionArea;
    @ApiModelProperty("预警时间")
    private String warningTime;
}
