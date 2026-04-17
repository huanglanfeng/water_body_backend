package com.blackwater.service.Impl;

import com.blackwater.dao.DeviceDao;
import com.blackwater.entity.Camera;
import com.blackwater.entity.Device;
import com.blackwater.entity.Sensor;
import com.blackwater.service.DeviceService;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    private DeviceDao deviceDao;
    /**
     * 查询站点的传感器信息接口
     * @param site
     * @return
     */
    @Override
    public Result<List<Sensor>> selectSensorBySite(String site) {
        log.info("传入的site:{}",site);
        try {
            List<Sensor> sensors = deviceDao.selectSensorBySite(site);
            log.info("查询传感器信息成功");
            return new Result<List<Sensor>>(Code.OK,"查询传感器信息成功",sensors);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常",null);
        }
    }

    /**
     * 查询站点的摄像头信息接口
     * @param site
     * @return
     */
    @Override
    public Result<List<Camera>> selectCameraBySite(String site) {
        log.info("传入的site:{}",site);
        try {
            List<Camera> cameras = deviceDao.selectCameraBySite(site);
            log.info("查询摄像头信息成功");
            return new Result<List<Camera>>(Code.OK,"查询摄像头信息成功",cameras);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常",null);
        }
    }
    /**
     *查询设备接口
     * @return
     */
    @Override
    public Result<List<Device>> selectDeviceData() {
        List<Device> devices = deviceDao.selectDeviceData();
        log.info("查询设备成功");
        return new Result<>(Code.OK,"查询设备成功",devices);
    }
    /**
     * 删除设备接口
     *
     * @return
     */
    @Override
    public Result<String> deleteDeviceData(int id) {
        try {
            deviceDao.deleteDeviceData(id);
            log.info("删除设备成功");
            return new Result<>(Code.OK,"查询设备成功");
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常",null);
        }
    }
}
