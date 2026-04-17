package com.blackwater.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackwater.entity.DissolvedOxygen;
import com.blackwater.entity.ElementAndDissolve.ElementAndDissolve;
import com.blackwater.config.until.Result;

public interface DissolvedOxygenService extends IService<DissolvedOxygen> {


   /**
    * 查询元素和溶解氧
    * @return
    */

   Result<ElementAndDissolve> selectElementAndDissolve();

   String addDO(DissolvedOxygen dissolvedOxygen);



}
