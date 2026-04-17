package com.blackwater.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackwater.entity.Gas.*;
import com.blackwater.entity.GasUseless;
import org.springframework.stereotype.Repository;

@Repository
public interface GasDao extends BaseMapper<GasUseless> {

    /**
     * 根据地区删除气体浓度数据
     * @param address 地区
     * @return 返回是否删除成功
     */
     int deleteGasByAddress(String address);


    /**
     * 根据id 修改气体数据
     * @param id 序号
     * @return 返回是否修改成功
     */
     int updateGasById(int id);
    Ammonia selectAmmonia();
    Carbon selectCarbon();
    Hydrogen selectHydrogen();
    Nitrogen selectNitrogen();
    Sulfur selectSulfur();



}
