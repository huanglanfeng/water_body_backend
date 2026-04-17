package com.blackwater.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddressData {


    @ApiModelProperty("账号")
    private int id;
    @ApiModelProperty("站点")
    private String site;
    @ApiModelProperty("位置")
    private String address;
    @ApiModelProperty("河流")
    private String river;
    @ApiModelProperty("污染指数")
    private int pollutionIndex;
    @ApiModelProperty("预警次数")
    private int alertNumber;
    @ApiModelProperty("预警时刻")
    private String alertTime;
    @ApiModelProperty("预警等级")
    private int alertLevel;
    @ApiModelProperty("经度")
    private double longitude ;
    @ApiModelProperty("纬度")
    private double latitude;
    @ApiModelProperty("水质情况")
    private String waterQuality;
    @ApiModelProperty("垃圾数量")
    private String garbageNumber;
    @ApiModelProperty("重金属含量")
    private String metalNumber;
    @ApiModelProperty("微生物含量")
    private String organismNumber;

}
