package com.blackwater.entity.Gas;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Gas {
    @ApiModelProperty("氨气")
    private ArrayList<Double> Ammonia;
    @ApiModelProperty("二氧化硫 ")
    private ArrayList<Double> Sulfur;
    @ApiModelProperty("硫化氢 ")
    private ArrayList<Double> Hydrogen;
    @ApiModelProperty("二氧化氮")
    private ArrayList<Double> Nitrogen;
    @ApiModelProperty("一氧化碳")
    private ArrayList<Double> Carbon;

}
