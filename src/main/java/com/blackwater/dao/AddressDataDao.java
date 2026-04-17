package com.blackwater.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackwater.entity.AddressData;
import com.blackwater.entity.AddressDataImageInfo;
import com.blackwater.entity.Level.LevelWeek;
import com.blackwater.entity.Percent.Excellent;
import com.blackwater.entity.Percent.Good;
import com.blackwater.entity.Percent.Pollute;
import com.blackwater.entity.Percent.Serious;
import com.blackwater.entity.WaterAmount;
import com.blackwater.entity.warning.Warning;
import com.blackwater.entity.warning.WarningScreen;
import com.blackwater.entity.warning.WarningSite;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDataDao extends BaseMapper<AddressData> {

    /**
     * 查询所有站点信息
     */

    List<AddressData> selectAddressData();

    void deleteByAlert_number(Integer alert_number);

    Excellent selectExcellent();

    Good selectGood();

    Pollute selectPollute();

    Serious selectSerious();

    LevelWeek selectLevelWeek();

    List<Warning> selectWarning();

    WarningScreen selectWarningBySite(String site);

    String selectWarningPhotos(String site);

    @Select(" select site_id from statistics where water_quality_parameters < 100;")
    List<WarningSite> getSiteByWaterQualityParameters();

    @Select(" select * from water_amount;")
    List<WaterAmount> getWaterAmount();


}
