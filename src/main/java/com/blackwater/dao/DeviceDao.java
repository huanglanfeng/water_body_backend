package com.blackwater.dao;


import com.blackwater.entity.Camera;
import com.blackwater.entity.Device;
import com.blackwater.entity.Sensor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao {
    /**
     * 查询站点的传感器信息
     *
     * @param site
     * @return
     */
    List<Sensor> selectSensorBySite(String site);

    /**
     * 查询站点的摄像头信息
     *
     * @param site
     * @return
     */
    List<Camera> selectCameraBySite(String site);

    /**
     * 查询站点的摄像头数量
     *
     * @param site
     * @return
     */
    int selectCameraNumberBySite(String site);

    /**
     * 查询设备
     *
     * @return
     */

    List<Device> selectDeviceData();
    /**
     * 删除设备接口
     *
     * @return
     */
      int deleteDeviceData(int id);
}
