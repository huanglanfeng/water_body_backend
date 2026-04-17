package com.blackwater.service;

import com.blackwater.entity.Camera;
import com.blackwater.entity.Device;
import com.blackwater.entity.Sensor;
import com.blackwater.config.until.Result;

import java.util.List;

public interface DeviceService {
    /**
     * 查询站点的传感器信息接口
     * @param site
     * @return
     */
    Result<List<Sensor>> selectSensorBySite(String site);


    /**
     * 查询站点的摄像头信息接口
     * @param site
     * @return
     */
    Result<List<Camera>> selectCameraBySite(String site);


    /**
     *查询设备接口
     * @return
     */
    Result<List<Device>> selectDeviceData();
    /**
     * 删除设备接口
     *
     * @return
     */
    Result<String> deleteDeviceData(int id);


}
