package com.blackwater.entity.Water;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class WaterData {
    @ApiModelProperty("序号")
    private int id;

    //    @ApiModelProperty("水温7天")
//    private ArrayList<Double> wtw;
//    @ApiModelProperty("水量7天")
//    private ArrayList<Double> waw;
//    @ApiModelProperty("水ph7天")
//    private ArrayList<Double> wpw;
    @ApiModelProperty("温度")
    private double temperature;
    @ApiModelProperty("ph")
    private double ph;
    @ApiModelProperty("水量")
    private double amount;
    @ApiModelProperty("溶解氧浓度")
    private double dissolvedOxygenConcentration;
    @ApiModelProperty("浊度")
    private double turbidity;

    @ApiModelProperty("地区")
    private String siteId;
    @ApiModelProperty("t1")
    private double t1;
    @ApiModelProperty("t2")
    private double t2;
    @ApiModelProperty("t3")
    private double t3;
    @ApiModelProperty("t4")
    private double t4;
    @ApiModelProperty("t5")
    private double t5;
    @ApiModelProperty("t6")
    private double t6;
    @ApiModelProperty("t7")
    private double t7;
    @ApiModelProperty("a1")
    private double a1;

    @ApiModelProperty("a2")
    private double a2;
    @ApiModelProperty("a3")
    private double a3;
    @ApiModelProperty("a4")
    private double a4;
    @ApiModelProperty("a5")
    private double a5;
    @ApiModelProperty("a6")
    private double a6;
    @ApiModelProperty("a7")
    private double a7;
    @ApiModelProperty("p1")
    private double p1;
    @ApiModelProperty("p2")
    private double p2;
    @ApiModelProperty("p3")
    private double p3;
    @ApiModelProperty("p4")
    private double p4;
    @ApiModelProperty("p5")
    private double p5;
    @ApiModelProperty("p6")
    private double p6;
    @ApiModelProperty("p7")
    private double p7;

}
