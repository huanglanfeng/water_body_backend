package com.blackwater.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blackwater.entity.Gas.Gas;
import com.blackwater.entity.GasUseless;
import com.blackwater.config.until.Result;

public interface GasService extends IService<GasUseless> {

    /**
     * 添加气体浓度接口
     *
     * @param gas 包含（氨气、一氧化氮 等）
     * @return 返回是否添加成功
     */
    Result<String> addGas(GasUseless gas);

    /**
     * 根据地区 删除气体浓度接口
     *
     * @param address 地区
     * @return 返回是否删除成功
     */
    Result<String> deleteGasByAddress(String address);


    /**
     * 根据序号 修改气体浓度接口
     *
     * @param gas
     * @return
     */
    Result<String> modifyById(int id);

    /**
     * 查询气体(7天)
     *
     * @return
     */

    Result<Gas> selectGas();


}
