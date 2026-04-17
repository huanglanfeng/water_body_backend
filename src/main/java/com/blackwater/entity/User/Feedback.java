package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Feedback {
    @ApiModelProperty("序号")
    private Integer id;
    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("反馈账号")
    private String account;
    @ApiModelProperty("反馈内容")
    private String content;
    @ApiModelProperty("地点")
    private String site;
}
