package com.blackwater.entity.Heavy;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class Radiation {
    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("放射元素")
    private String radioactiveElement;
    @ApiModelProperty("异常地区")
    private String exceptionArea;
    @ApiModelProperty("预警时间")
    private String warningTime;
}
