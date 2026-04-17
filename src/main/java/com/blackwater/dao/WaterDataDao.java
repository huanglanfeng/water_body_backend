package com.blackwater.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackwater.entity.Water.WaterAmountWeek;
import com.blackwater.entity.Water.WaterData;
import com.blackwater.entity.Water.WaterPhWeek;
import com.blackwater.entity.Water.WaterTemperatureWeek;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterDataDao extends BaseMapper<WaterData> {

    List<WaterData> selectWaterBySite();

    WaterTemperatureWeek selectTemperatureWeekBySite(String site);

    WaterAmountWeek selectAmountWeekBySite(String site);

    WaterPhWeek selectPhWeekBySite(String site);


}
