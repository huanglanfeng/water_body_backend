package com.blackwater.entity.warning;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Warning {
    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("地点")
    private String site;
    @ApiModelProperty("警告内容")
    private String warning;
    @ApiModelProperty("值")
    private double value;
    @ApiModelProperty("时间")
    private String time;
}
