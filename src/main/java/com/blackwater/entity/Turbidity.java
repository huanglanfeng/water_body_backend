package com.blackwater.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Turbidity {
    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("位点")
    private String site;
    @ApiModelProperty("磷")
    private double phos;
    @ApiModelProperty("硫")
    private double sulfur;
    @ApiModelProperty("钙")
    private double calcium;
    @ApiModelProperty("浊度/TU")
    private double turbidity;


}
