package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterFeedback {
    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("地点")
    private String site;
}
