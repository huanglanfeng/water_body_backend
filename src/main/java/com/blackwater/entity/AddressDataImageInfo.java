package com.blackwater.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddressDataImageInfo {

    @ApiModelProperty("序号")
    private int id;
    @ApiModelProperty("站点")
    private String site;
    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("预警等级")
    private Integer alterLevel;
    @ApiModelProperty("污染源")
    private String pollutant;
    @ApiModelProperty("人员")
    private String people;
    @ApiModelProperty("内容")
    private String content;
}
