package com.blackwater.controller;

import com.blackwater.config.until.Result;
import com.blackwater.entity.Water.WaterData;
import com.blackwater.service.WaterDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "水信息")
@RequestMapping("/waterdata")
public class WaterDataController {

    @Autowired
    private WaterDataService waterDataService;

    /**
     *
     * @param waterData
     * @return
     */

    @PostMapping("/add")
    @ApiOperation(value = "添加水信息",notes = "添加水信息接口")
    @ApiResponses({
            @ApiResponse(code = 10001, message = "添加水信息成功状态码"),
            @ApiResponse(code = 10000, message = "添加水信息失败状态码")
    })
    public Result<String> addWaterQuality(@RequestBody WaterData waterData){
        return waterDataService.addWaterData(waterData);
    }
    /**
     *查询地区水信息
     * @return
     */
    @PostMapping("/select/water/data")
    @ApiOperation(value = "查地区水信息",notes = "查地区水信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查地区水信息 成功状态码"),
            @ApiResponse(code = 50000, message = "系统异常状态码")
    })
    public Result<List<WaterData>> selectWaterData(){
        return waterDataService.selectWaterData();
    }


}
