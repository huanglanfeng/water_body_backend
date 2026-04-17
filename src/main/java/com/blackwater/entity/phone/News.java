package com.blackwater.entity.phone;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class News {

    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("图片")
    private String photos;
    @ApiModelProperty("时间")
    private String time;
}
