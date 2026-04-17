package com.blackwater.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackwater.dao.DissolvedOxygenDao;
import com.blackwater.entity.DissolvedOxygen;
import com.blackwater.entity.ElementAndDissolve.*;
import com.blackwater.service.DissolvedOxygenService;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class DissolvedOxygenDaoImpl extends ServiceImpl<DissolvedOxygenDao, DissolvedOxygen> implements DissolvedOxygenService {
    @Autowired
    private DissolvedOxygenDao dissolvedOxygenDao;


    @Override
    public Result<ElementAndDissolve> selectElementAndDissolve() {
        ElementAndDissolve elementAndDissolve = dissolvedOxygenDao.selectElementAndDissolve();

        //查询无机盐
        ArrayList<Double> SALT = new ArrayList<>();
        Salt salt = dissolvedOxygenDao.selectSalt();
        SALT.add(salt.getSalt1());
        SALT.add(salt.getSalt2());
        SALT.add(salt.getSalt3());
        SALT.add(salt.getSalt4());
        SALT.add(salt.getSalt5());
        elementAndDissolve.setSalt(SALT);
        //查询有机化合物
        ArrayList<Double> ORGANIC = new ArrayList<>();
        Organic organic = dissolvedOxygenDao.selectOrganic();
        ORGANIC.add(organic.getOrganic1());
        ORGANIC.add(organic.getOrganic2());
        ORGANIC.add(organic.getOrganic3());
        ORGANIC.add(organic.getOrganic4());
        ORGANIC.add(organic.getOrganic5());
        elementAndDissolve.setOrganic(ORGANIC);
        //查询无机化合物
        ArrayList<Double> INORGANIC = new ArrayList<>();
        Inorganic inorganic = dissolvedOxygenDao.selectInorganic();
        INORGANIC.add(inorganic.getInorganic1());
        INORGANIC.add(inorganic.getInorganic2());
        INORGANIC.add(inorganic.getInorganic3());
        INORGANIC.add(inorganic.getInorganic4());
        INORGANIC.add(inorganic.getInorganic5());
        elementAndDissolve.setInorganic(INORGANIC);
        //查询重金属
        ArrayList<Double> MENTAL = new ArrayList<>();
        Metal metal = dissolvedOxygenDao.selectMetal();
        MENTAL.add(metal.getMetal1());
        MENTAL.add(metal.getMetal2());
        MENTAL.add(metal.getMetal3());
        MENTAL.add(metal.getMetal4());
        MENTAL.add(metal.getMetal5());
        elementAndDissolve.setMetal(MENTAL);
        //查询电导率
        ArrayList<Double> CONDUCTIVITY = new ArrayList<>();
        Conductivity conductivity = dissolvedOxygenDao.selectConductivity();
        CONDUCTIVITY.add(conductivity.getConductivity1());
        CONDUCTIVITY.add(conductivity.getConductivity2());
        CONDUCTIVITY.add(conductivity.getConductivity3());
        CONDUCTIVITY.add(conductivity.getConductivity4());
        CONDUCTIVITY.add(conductivity.getConductivity5());
        elementAndDissolve.setConductivity(CONDUCTIVITY);
        //查询溶解氧
        ArrayList<Double> DISSOLVE = new ArrayList<>();
        Dissolve dissolve = dissolvedOxygenDao.selectDissolve();
        DISSOLVE.add(dissolve.getDissolve1());
        DISSOLVE.add(dissolve.getDissolve2());
        DISSOLVE.add(dissolve.getDissolve3());
        DISSOLVE.add(dissolve.getDissolve4());
        DISSOLVE.add(dissolve.getDissolve5());
        DISSOLVE.add(dissolve.getDissolve6());
        DISSOLVE.add(dissolve.getDissolve7());
        DISSOLVE.add(dissolve.getDissolve8());
        elementAndDissolve.setDissolve(DISSOLVE);
        log.info("查询元素和溶解氧成功");
        return new Result<ElementAndDissolve>(Code.OK,"查询元素和溶解氧成功",elementAndDissolve);
    }

    @Override
    public String addDO(DissolvedOxygen dissolvedOxygen) {
        try {
            dissolvedOxygenDao.insert(dissolvedOxygen);
            return "添加成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }
    }











}
