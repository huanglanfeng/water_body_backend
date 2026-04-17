package com.blackwater.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackwater.dao.WaterDataDao;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import com.blackwater.entity.Water.WaterData;
import com.blackwater.service.WaterDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class WaterDataServiceImpl extends ServiceImpl<WaterDataDao, WaterData> implements WaterDataService {

    @Autowired
    private WaterDataDao waterDataDao;

    /**
     * 查地区水信息接口
     *
     * @return
     */
    @Override
    public Result<List<WaterData>> selectWaterData() {
        try {
            List<WaterData> waterData = waterDataDao.selectWaterBySite();
            log.info("查询地区水信息成功");
            return new Result<List<WaterData>>(Code.OK, "查询地区水信息成功", waterData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出现异常");
            return new Result<>(Code.EXCEPTION, "系统出现异常");
        }
    }

    /**
     * 添加水信息接口
     *
     * @param waterData 包含 温度、ph、水量
     * @return 返回是否添加成功
     */
    @Override
    public Result<String> addWaterData(WaterData waterData) {
        log.info("传入的水信息:" + waterData);
        try {
            int insert = waterDataDao.insert(waterData);
            if (insert == 0) {
                log.error("添加水信息失败");
                return new Result<String>(Code.ADD_ERR, "添加水信息失败", null);
            } else {
                log.info("添加水信息成功");
                return new Result<String>(Code.ADD_OK, "添加水信息成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
