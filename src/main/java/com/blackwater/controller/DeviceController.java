package com.blackwater.controller;

import com.blackwater.entity.Camera;
import com.blackwater.entity.Device;
import com.blackwater.entity.Sensor;
import com.blackwater.service.DeviceService;
import com.blackwater.config.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "设备")
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 查询站点的传感器信息接口
     *
     * @param site
     * @return
     */

    @PostMapping("/select/site/sensor")
    @ApiOperation(value = "查站点传感器信息", notes = "查站点传感器信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查站点传感器信息 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Sensor>> selectSensorBySite(String site) {
        return deviceService.selectSensorBySite(site);
    }

    /**
     * 查询站点的摄像头信息接口
     *
     * @param site
     * @return
     */

    @PostMapping("/select/site/camera")
    @ApiOperation(value = "查站点摄像头信息", notes = "查站点摄像头信息 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查站点摄像头信息 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Camera>> selectCameraBySite(String site) {
        return deviceService.selectCameraBySite(site);
    }

    /**
     * 查询设备
     *
     * @return
     */
    @PostMapping("/select/device/data")
    @ApiOperation(value = "查询设备", notes = "查询设备 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询设备 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Device>> selectDeviceData() {
        return deviceService.selectDeviceData();
    }
    /**
     * 删除设备接口
     *
     * @return
     */
    @GetMapping ("/delete/device/data")
    @ApiOperation(value = "删除设备", notes = "删除设备 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "删除设备 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<String> deleteDeviceData(@RequestParam("id") int id) {
        return deviceService.deleteDeviceData(id);
    }
}
