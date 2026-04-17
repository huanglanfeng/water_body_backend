package com.blackwater.entity.warning;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WarningSite {
    @ApiModelProperty("地点")
    private String siteId;
}
