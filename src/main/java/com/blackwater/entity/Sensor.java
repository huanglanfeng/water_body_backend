package com.blackwater.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Sensor {
    @ApiModelProperty("设备编号")
    private int id;
    @ApiModelProperty("设备状态")
    private String status;
    @ApiModelProperty("更新时间")
    private String updateTime;


}
