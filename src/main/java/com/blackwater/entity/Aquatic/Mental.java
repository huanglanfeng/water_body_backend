package com.blackwater.entity.Aquatic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Mental {
    @ApiModelProperty("地区")
    private String exceptionArea;
    @ApiModelProperty("纳")
    private double sodium;
    @ApiModelProperty("汞")
    private double mercury;
    @ApiModelProperty("钙")
    private double calcium;
    @ApiModelProperty("锌")
    private double zinc;
    @ApiModelProperty("镁")
    private double magnesium;
    @ApiModelProperty("检测时间")
    private String detectTime;
}
