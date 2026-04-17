package com.blackwater.entity.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Notice {

    @ApiModelProperty("序号")
    private Integer id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("发布人")
    private String name;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("发布时间")
    private String release_time;

}
