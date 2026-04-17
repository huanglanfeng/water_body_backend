package com.blackwater.entity.phone;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PhoneData {

    @ApiModelProperty("编号")
    private int id;
    @ApiModelProperty("站点名称")
    private String site;
    @ApiModelProperty("站点位置")
    private String address;
    @ApiModelProperty("预警时刻")
    private String alertTime;
    @ApiModelProperty("预警级别")
    private int alertLevel;
    @ApiModelProperty("水质参数")
    private int waterQualityParameters;
    @ApiModelProperty("水体温度")
    private double t7;
    @ApiModelProperty("水体ph")
    private double ph;
    @ApiModelProperty("浊度")
    private double turbidity;
    @ApiModelProperty("氨气")
    private double ammonia;
    @ApiModelProperty("二氧化硫")
    private double sulfurDioxide;
    @ApiModelProperty("硫化氢")
    private double hydrogenSulfide;
    @ApiModelProperty("二氧化氮")
    private double nitrogenDioxide;
    @ApiModelProperty("一氧化碳")
    private double carbonMonoxide;
    @ApiModelProperty("电导率")
    private double conductivity;
    @ApiModelProperty("经度")
    private double longitude;
    @ApiModelProperty("纬度")
    private double latitude;

}
