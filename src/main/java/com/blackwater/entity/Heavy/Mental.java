package com.blackwater.entity.Heavy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Mental {
    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("元素")
    private String element;
    @ApiModelProperty("含量")
    private String content;
    @ApiModelProperty("设备编号")
    private String deviceId;
    @ApiModelProperty("异常地区")
    private String exceptionArea;
    @ApiModelProperty("预警时间")
    private String warningTime;
}
