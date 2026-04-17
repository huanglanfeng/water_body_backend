package com.blackwater.entity.Aquatic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Organism {
    @ApiModelProperty("地区")
    private String exceptionArea;
    @ApiModelProperty("细菌")
    private double germ;
    @ApiModelProperty("真菌")
    private double fungus;
    @ApiModelProperty("原生动物")
    private double animal;
    @ApiModelProperty("噬菌体")
    private double phage;
    @ApiModelProperty("检测时间")
    private String detectTime;
}
