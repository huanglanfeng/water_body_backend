package com.blackwater.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Device {

    @ApiModelProperty("设备编号")
    private int id;
    @ApiModelProperty("设备类型")
    private String type;
    @ApiModelProperty("设备状态")
    private String status;
    @ApiModelProperty("更新时间")
    private String updateTime;
    @ApiModelProperty("监测地区")
    private String siteId;
    @ApiModelProperty("间隔")
    private int interval;

}


