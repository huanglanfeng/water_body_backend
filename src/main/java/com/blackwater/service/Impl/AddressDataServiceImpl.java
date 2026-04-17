package com.blackwater.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackwater.dao.AddressDataDao;
import com.blackwater.entity.AddressData;
import com.blackwater.entity.AddressDataImageInfo;
import com.blackwater.entity.Level.Level;
import com.blackwater.entity.Level.LevelWeek;
import com.blackwater.entity.Percent.*;
import com.blackwater.entity.WaterAmount;
import com.blackwater.entity.warning.Warning;
import com.blackwater.entity.warning.WarningScreen;
import com.blackwater.entity.warning.WarningSite;
import com.blackwater.service.AddressDataService;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AddressDataServiceImpl extends ServiceImpl<AddressDataDao, AddressData> implements AddressDataService {

    @Autowired
    private AddressDataDao addressDataDao;

    /**
     * 查询所有站点信息接口
     *
     * @return
     */
    @Override
    public Result<List<AddressData>> selectAddressData() {
        log.info("调用查全站点");
        try {
            List<AddressData> addressData = addressDataDao.selectAddressData();
            log.info("查全站点 成功");
            return new Result<List<AddressData>>(Code.OK, "查全站点 成功", addressData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出现异常");
            return new Result<>(Code.EXCEPTION, "系统出现异常");
        }
    }

    /**
     * 查询污染百分比
     *
     * @return
     */
    @Override
    public Result<Percent> selectPercent() {
        Percent percent = new Percent();
        //查询优秀
        ArrayList<Double> EXCELLENT = new ArrayList<>();
        Excellent excellent = addressDataDao.selectExcellent();
        EXCELLENT.add(excellent.getExcellent1());
        EXCELLENT.add(excellent.getExcellent2());
        EXCELLENT.add(excellent.getExcellent3());
        EXCELLENT.add(excellent.getExcellent4());
        EXCELLENT.add(excellent.getExcellent5());
        EXCELLENT.add(excellent.getExcellent6());
        percent.setExcellent(EXCELLENT);
        //查询良好
        ArrayList<Double> GOOD = new ArrayList<>();
        Good good = addressDataDao.selectGood();
        GOOD.add(good.getGood1());
        GOOD.add(good.getGood2());
        GOOD.add(good.getGood3());
        GOOD.add(good.getGood4());
        GOOD.add(good.getGood5());
        GOOD.add(good.getGood6());
        percent.setGood(GOOD);
        //查询污染
        ArrayList<Double> POLLUTE = new ArrayList<>();
        Pollute pollute = addressDataDao.selectPollute();
        POLLUTE.add(pollute.getPollute1());
        POLLUTE.add(pollute.getPollute2());
        POLLUTE.add(pollute.getPollute3());
        POLLUTE.add(pollute.getPollute4());
        POLLUTE.add(pollute.getPollute5());
        POLLUTE.add(pollute.getPollute6());
        percent.setPollute(POLLUTE);
        //查询严重污染
        ArrayList<Double> SERIOUS = new ArrayList<>();
        Serious serious = addressDataDao.selectSerious();
        SERIOUS.add(serious.getSerious1());
        SERIOUS.add(serious.getSerious2());
        SERIOUS.add(serious.getSerious3());
        SERIOUS.add(serious.getSerious4());
        SERIOUS.add(serious.getSerious5());
        SERIOUS.add(serious.getSerious6());
        percent.setSerious(SERIOUS);
        log.info("查询污染百分比成功");
        return new Result<Percent>(Code.OK, "查询污染百分比成功", percent);
    }

    /**
     * 查询等级
     *
     * @return
     */
    @Override
    public Result<Level> selectLevel() {
        Level level = new Level();
        ArrayList<Double> LEVELWEEK = new ArrayList<>();
        LevelWeek levelWeek = addressDataDao.selectLevelWeek();
        LEVELWEEK.add(levelWeek.getLevel1());
        LEVELWEEK.add(levelWeek.getLevel2());
        LEVELWEEK.add(levelWeek.getLevel3());
        LEVELWEEK.add(levelWeek.getLevel4());
        LEVELWEEK.add(levelWeek.getLevel5());
        LEVELWEEK.add(levelWeek.getLevel6());
        LEVELWEEK.add(levelWeek.getLevel7());
        level.setLevel(LEVELWEEK);
        log.info("查询等级成功");
        return new Result<>(Code.OK, "查询等级成功", level);
    }

    /**
     * 查询超标警告
     *
     * @return
     */
    @Override
    public Result<List<Warning>> selectWarning() {
        try {
            List<Warning> warnings = addressDataDao.selectWarning();
            log.info("查询超标警告成功");
            return new Result<>(Code.OK, "查询超标警告成功", warnings);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出现异常");
            return new Result<>(Code.EXCEPTION, "系统出现异常");
        }

    }


    @Override
    public String addAddressData(AddressData addressData) {
        try {

            addressDataDao.insert(addressData);
            return "1 成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "0 失败";
        }


    }

    @Override
    public String deleteByAlert_number(Integer alert_number) {

        try {
            addressDataDao.deleteByAlert_number(alert_number);
            return "1 成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }


    }

    /**
     * 查询地区根据水参（大屏）
     *
     * @return
     */
    @Override
    public Result<List<WarningSite>> getSiteByWaterQualityParameters() {
        List<WarningSite> siteByWaterQualityParameters = addressDataDao.getSiteByWaterQualityParameters();
        return new Result<>(Code.OK, "查询成功", siteByWaterQualityParameters);
    }

    /**
     * 查询地区预警信息（大屏）
     *
     * @return
     */
    @Override
    public Result<WarningScreen> selectWarningBySite(String site) {
        try {
            WarningScreen warningScreen = addressDataDao.selectWarningBySite(site);
            String s = addressDataDao.selectWarningPhotos(site);
            String[] split = s.split("\\|\\|\\|");
//            ArrayList<String> photoList = new ArrayList<>(Arrays.asList(split));
            warningScreen.setPhotoOne(split[1]);
            warningScreen.setPhotoTwo(split[2]);
            warningScreen.setPhotoThree(split[3]);
            return new Result<>(Code.OK,"查询成功",warningScreen);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(Code.OK, "查询失败", null);
        }

    }
    /**
     * 查询地区（市）水量（大屏）
     * @return
     */
    @Override
    public Result<List<WaterAmount>> getWaterAmount() {
        List<WaterAmount> waterAmount = addressDataDao.getWaterAmount();
        return new Result<>(Code.OK,"查询成功",waterAmount);
    }



}