package com.blackwater.controller;

import com.blackwater.entity.AddressData;
import com.blackwater.entity.AddressDataImageInfo;
import com.blackwater.entity.Level.Level;
import com.blackwater.entity.Percent.Percent;
import com.blackwater.entity.WaterAmount;
import com.blackwater.entity.warning.Warning;
import com.blackwater.entity.warning.WarningScreen;
import com.blackwater.entity.warning.WarningSite;
import com.blackwater.service.AddressDataService;
import com.blackwater.config.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "地区")
@RequestMapping("/addressdata")
public class AddressDataController {

    @Autowired
    private AddressDataService addressDataService;

    /**
     * 查询所有站点信息接口
     *
     * @return
     */
    @PostMapping("/select/address/data")
    @ApiOperation(value = "查询所有站点信息", notes = "查询所有站点信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查全站点 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<AddressData>> selectAddressData() {
        return addressDataService.selectAddressData();
    }

    /**
     * 查询污染百分比
     *
     * @return
     */
    @PostMapping("/select/percent")
    @ApiOperation(value = "查询污染百分比", notes = "查询污染百分比 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查全站点 成功状态码"),
    })
    public Result<Percent> selectPercent() {
        return addressDataService.selectPercent();
    }

    /**
     * 查询等级
     *
     * @return
     */
    @PostMapping("/select/level")
    @ApiOperation(value = "查询等级", notes = "查询等级 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询等级 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<Level> selectLevel() {
        return addressDataService.selectLevel();
    }

    /**
     * 查询超标警告
     *
     * @return
     */
    @PostMapping("/select/warning")
    @ApiOperation(value = "查询超标警告", notes = "查询超标警告 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询超标警告 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Warning>> selectWarning() {
        return addressDataService.selectWarning();
    }


    @PostMapping("/add")
    public String addAddressData(@RequestBody AddressData addressData) {
        return addressDataService.addAddressData(addressData);
    }

    @DeleteMapping("/delete/by/alert_number")
    public String deleteByAlert_number(Integer alert_number) {
        return addressDataService.deleteByAlert_number(alert_number);
    }


    /**
     * 查询地区水参（大屏）
     *
     * @return
     */
    @PostMapping("/get/site/by/waterQualityParameters")
    @ApiOperation(value = "查询地区水参(大屏)", notes = "查询地区水参(大屏) 接口")
    @ApiResponses(
            @ApiResponse(code = 1, message = "查询地区水参(大屏) 成功状态码")
    )
    public Result<List<WarningSite>> getSiteByWaterQualityParameters() {
        return addressDataService.getSiteByWaterQualityParameters();
    }


    /**
     * 查询地区预警信息（大屏）
     *
     * @return
     */
    @GetMapping("/get/warning/screen")
    @ApiOperation(value = "查询地区预警信息(大屏)", notes = "查询地区预警信息(大屏) 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询地区预警信息(大屏) 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<WarningScreen> selectWarningScreen(@RequestParam("siteId") String siteId) {

        return addressDataService.selectWarningBySite(siteId);
    }


    /**
     * 查询地区（市）水量（大屏）
     *
     * @return
     */
    @GetMapping("/get/water/amount")
    @ApiOperation(value = "查询地区市水量（大屏）", notes = "查询地区市水量（大屏） 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询地区市水量（大屏） 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<WaterAmount>> getWaterAmount() {
        return addressDataService.getWaterAmount();
    }




}
