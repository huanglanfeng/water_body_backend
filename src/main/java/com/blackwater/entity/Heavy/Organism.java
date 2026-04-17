package com.blackwater.entity.Heavy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Organism {
    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("微生物名字")
    private String name;
    @ApiModelProperty("数量")
    private int number;
    @ApiModelProperty("异常地区")
    private String exceptionArea;
    @ApiModelProperty("预警时间")
    private String warningTime;
}
