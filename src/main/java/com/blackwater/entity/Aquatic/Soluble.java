package com.blackwater.entity.Aquatic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Soluble {
    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("磷")
    private double phos;
    @ApiModelProperty("硫")
    private double sulfur;
    @ApiModelProperty("氮")
    private double nitrogen;
    @ApiModelProperty("溶氧量")
    private double dissolvedOxygen;
    @ApiModelProperty("检测时间")
    private String detectTime;
    @ApiModelProperty("检测地区")
    private String site;
}
