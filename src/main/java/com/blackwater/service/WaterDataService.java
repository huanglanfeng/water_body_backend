package com.blackwater.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackwater.config.until.Result;
import com.blackwater.entity.Water.WaterData;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public interface WaterDataService extends IService<WaterData> {

    /**
     * 查地区水信息接口
     * @return
     */
   Result<List<WaterData>> selectWaterData();


    /**
     * 添加水信息接口
     * @param waterData 包含 温度、ph、水量
     * @return 返回是否添加成功
     */
   Result<String> addWaterData(WaterData waterData);


}
