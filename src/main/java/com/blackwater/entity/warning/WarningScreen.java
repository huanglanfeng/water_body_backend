package com.blackwater.entity.warning;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WarningScreen {

    @ApiModelProperty("地点")
    private String site;
    @ApiModelProperty("预警次数")
    private int alertNumber;
    @ApiModelProperty("预警级别")
    private int alertLevel;
    @ApiModelProperty("水质评分")
    private int waterQualityParameters;
    @ApiModelProperty("图片1")
    private String photoOne;
    @ApiModelProperty("图片2")
    private String photoTwo;
    @ApiModelProperty("图片3")
    private String photoThree;

    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("污染源")
    private String pollutant;
    @ApiModelProperty("人员")
    private String people;
    @ApiModelProperty("内容")
    private String content;


}
