package com.blackwater.entity.HeavyContent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Radiation {
    @ApiModelProperty("含量1")
    private double content1;
    @ApiModelProperty("含量2")
    private double content2;
    @ApiModelProperty("含量3")
    private double content3;
    @ApiModelProperty("含量4")
    private double content4;
    @ApiModelProperty("含量5")
    private double content5;
}
