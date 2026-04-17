package com.blackwater.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.blackwater.entity.AddressData;
import com.blackwater.entity.AddressDataImageInfo;
import com.blackwater.entity.Level.Level;
import com.blackwater.entity.Percent.Percent;
import com.blackwater.entity.WaterAmount;
import com.blackwater.entity.warning.Warning;
import com.blackwater.config.until.Result;
import com.blackwater.entity.warning.WarningScreen;
import com.blackwater.entity.warning.WarningSite;

import java.util.List;

public interface AddressDataService extends IService<AddressData> {

    /**
     * 查询所有站点信息接口
     *
     * @return
     */
    Result<List<AddressData>> selectAddressData();

    /**
     * 查询污染百分比
     *
     * @return
     */
    Result<Percent> selectPercent();

    /**
     * 查询等级
     *
     * @return
     */
    Result<Level> selectLevel();

    /**
     * 查询超标警告
     *
     * @return
     */
    Result<List<Warning>> selectWarning();


    String addAddressData(AddressData addressData);

    String deleteByAlert_number(Integer alert_number);

    /**
     * 查询地区根据水参（大屏）
     *
     * @return
     */

    Result<List<WarningSite>> getSiteByWaterQualityParameters();

    /**
     * 查询地区预警信息（大屏）
     *
     * @return
     */
    Result<WarningScreen> selectWarningBySite(String site);

    /**
     * 查询地区（市）水量（大屏）
     *
     * @return
     */

    Result<List<WaterAmount>> getWaterAmount();



}
