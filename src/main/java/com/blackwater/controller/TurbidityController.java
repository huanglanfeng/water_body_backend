package com.blackwater.controller;

import com.blackwater.entity.Turbidity;
import com.blackwater.service.TurbidityService;
import com.blackwater.config.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "浊度")
@RequestMapping("/turbidity")
@Slf4j
public class TurbidityController {

    @Autowired
    private TurbidityService turbidityService;

    /**
     * 查询浊度
     *
     * @return
     */
    @PostMapping("select/turbidity/data")
    @ApiOperation(value = "查询浊度", notes = "查询浊度 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询浊度 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Turbidity>> selectTurbidityData() {
        return turbidityService.selectTurbidityData();
    }


    @PostMapping("/add")
//    @ApiOperation(value="添加浊度",produces = "application/octet-stream")
    public String addTurbidity(@RequestBody Turbidity turbidity){
        return turbidityService.addTurbidity(turbidity);
    }



}
