package com.blackwater.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackwater.entity.DissolvedOxygen;
import com.blackwater.entity.ElementAndDissolve.*;
import org.springframework.stereotype.Repository;

@Repository
public interface DissolvedOxygenDao extends BaseMapper<DissolvedOxygen> {



    ElementAndDissolve selectElementAndDissolve();

    Salt selectSalt();
    Organic selectOrganic();

    Inorganic selectInorganic();
    Metal selectMetal();
    Conductivity selectConductivity();
    Dissolve selectDissolve();


}
