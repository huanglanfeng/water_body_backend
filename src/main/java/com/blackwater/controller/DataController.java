package com.blackwater.controller;

import com.blackwater.entity.phone.Fault;
import com.blackwater.entity.phone.MapData;
import com.blackwater.entity.phone.News;
import com.blackwater.entity.phone.PhoneData;
import com.blackwater.phoneService.DataService;
import com.blackwater.config.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "app端")
@RequestMapping("/phone")
public class DataController {
    @Autowired
    private DataService dataService;



    /**
     *  查询所有信息(手机)
     * @return
     */
    @PostMapping("/select/all")
    @ApiOperation(value = "查询所有信息(手机)",notes = "查询所有信息(手机)接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询所有信息(手机) 成功状态码"),
            @ApiResponse(code = 50000, message = "系统出现异常 状态码")
    })
    public Result<List<PhoneData>> selectAllData(){
        return dataService.selectAllData();
    }



    /**
     *  添加故障信息(手机)
     * @return
     */
    @PostMapping("/add/fault")
    @ApiOperation(value = "添加故障信息(手机)",notes = "添加故障信息(手机)接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "添加故障信息(手机) 成功状态码"),
            @ApiResponse(code = 50000, message = "系统出现异常 状态码")
    })
    public Result<String> addFault(@RequestBody Fault fault){
        return dataService.addFault(fault);
    }


    /**
     * 随机查询一条新闻(手机)
     * @return
     */
    @PostMapping("/select/news")
    @ApiOperation(value = "随机查询一条新闻(手机)",notes = "随机查询一条新闻(手机)接口")
    public Result<News> selectNews(){
        return dataService.selectNews();
    }


    /**
     * 查询地图信息(手机)
     * @return
     */
    @PostMapping("/select/map")
    @ApiOperation(value = "查询地图信息(手机)",notes = "查询地图信息(手机)接口")
    public Result<List<MapData>> selectMap(){
        return dataService.selectMap();
    }

}
