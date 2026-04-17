package com.blackwater.entity.phone;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import javax.persistence.Column;

@Data

public class MapData {
    @ApiModelProperty("编号")
    private int id;
    @ApiModelProperty("站点名称")
//    @TableField(value = "site")
//    @Column(name = "site")
    private String site;
    @ApiModelProperty("经度")
    private double longitude;
    @ApiModelProperty("纬度")
    private double latitude;
}
