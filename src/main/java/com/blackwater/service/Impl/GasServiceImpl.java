package com.blackwater.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackwater.dao.GasDao;
import com.blackwater.entity.Gas.*;
import com.blackwater.entity.GasUseless;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import com.blackwater.service.GasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class GasServiceImpl extends ServiceImpl<GasDao, GasUseless> implements GasService {

    @Autowired
    private GasDao gasDao;

    /**
     * 添加气体浓度接口
     *
     * @param gas 包含（氨气、一氧化氮 等）
     * @return 返回是个添加成功
     */
    @Override
    public Result<String> addGas(GasUseless gas) {
        log.info("传入的气体浓度数据:" + gas);
        try {
            int insert = gasDao.insert(gas);
            if (insert == 0) {
                log.error("添加气体浓度失败");
                return new Result<String>(Code.ADD_ERR, "添加气体浓度失败", null);
            } else {
                log.info("添加气体浓度成功");
                return new Result<String>(Code.ADD_ERR, "添加气体浓度成功", null);
            }
        } catch (Exception e) {
            log.error("系统出现异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据地区删除气体浓度数据接口
     *
     * @param address 地区
     * @return 返回是否删除成功
     */
    @Override
    public Result<String> deleteGasByAddress(String address) {
        log.info("传入需要删除气体浓度的地区：" + address);
        try {
            int delete = gasDao.deleteGasByAddress(address);
            if (delete == 0) {
                log.error("删除气体浓度 失败 根据地区");
                return new Result<String>(Code.DELETE_ERR, "删除气体浓度 失败 根据地区", null);
            } else {
                log.info("删除气体浓度 成功 根据地区");
                return new Result<String>(Code.DELETE_OK, "删除气体浓度 成功 根据地区", null);
            }
        } catch (Exception e) {
            log.error("系统出现异常");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result<String> modifyById(int id) {
        log.info("传入需要修改的数据:" + id);
        try {
            int update = gasDao.updateGasById(id);
            if (update == 0) {
                log.error("修改气体数据 失败");
                return new Result<String>(Code.UPDATE_ERR, "修改气体数据 失败", null);
            } else {
                log.error("修改气体数据 成功");
                return new Result<String>(Code.UPDATE_OK, "修改气体数据 成功", null);
            }
        } catch (Exception e) {
            log.info("系统出现异常");
            e.printStackTrace();
            return new Result<String>(Code.EXCEPTION, "系统出现异常", null);
        }

    }

    /**
     * 查询气体(7天)
     *
     * @return
     */
    @Override
    public Result<Gas> selectGas() {
        Gas gas = new Gas();
        try {
            //查询氨气
            ArrayList<Double> AMMONIA = new ArrayList<>();
            Ammonia ammonia = gasDao.selectAmmonia();
            AMMONIA.add(ammonia.getAmmonia1());
            AMMONIA.add(ammonia.getAmmonia2());
            AMMONIA.add(ammonia.getAmmonia3());
            AMMONIA.add(ammonia.getAmmonia4());
            AMMONIA.add(ammonia.getAmmonia5());
            AMMONIA.add(ammonia.getAmmonia6());
            AMMONIA.add(ammonia.getAmmonia7());
            gas.setAmmonia(AMMONIA);
            //查询二氧化硫
            ArrayList<Double> SULFUR = new ArrayList<>();
            Sulfur sulfur = gasDao.selectSulfur();
            SULFUR.add(sulfur.getSulfur1());
            SULFUR.add(sulfur.getSulfur2());
            SULFUR.add(sulfur.getSulfur3());
            SULFUR.add(sulfur.getSulfur4());
            SULFUR.add(sulfur.getSulfur5());
            SULFUR.add(sulfur.getSulfur6());
            SULFUR.add(sulfur.getSulfur7());
            gas.setSulfur(SULFUR);
            //查询硫化氢
            ArrayList<Double> HYDROGEN = new ArrayList<>();
            Hydrogen hydrogen = gasDao.selectHydrogen();
            HYDROGEN.add(hydrogen.getHydrogen1());
            HYDROGEN.add(hydrogen.getHydrogen2());
            HYDROGEN.add(hydrogen.getHydrogen3());
            HYDROGEN.add(hydrogen.getHydrogen4());
            HYDROGEN.add(hydrogen.getHydrogen5());
            HYDROGEN.add(hydrogen.getHydrogen6());
            HYDROGEN.add(hydrogen.getHydrogen6());
            gas.setHydrogen(HYDROGEN);
            //查询二氧化氮
            ArrayList<Double> NITROGEN = new ArrayList<>();
            Nitrogen nitrogen = gasDao.selectNitrogen();
            NITROGEN.add(nitrogen.getNitrogen1());
            NITROGEN.add(nitrogen.getNitrogen2());
            NITROGEN.add(nitrogen.getNitrogen3());
            NITROGEN.add(nitrogen.getNitrogen4());
            NITROGEN.add(nitrogen.getNitrogen5());
            NITROGEN.add(nitrogen.getNitrogen6());
            NITROGEN.add(nitrogen.getNitrogen7());
            gas.setNitrogen(NITROGEN);
            //查询一氧化碳
            ArrayList<Double> CARBON = new ArrayList<>();
            Carbon carbon = gasDao.selectCarbon();
            CARBON.add(carbon.getCarbon1());
            CARBON.add(carbon.getCarbon2());
            CARBON.add(carbon.getCarbon3());
            CARBON.add(carbon.getCarbon4());
            CARBON.add(carbon.getCarbon5());
            CARBON.add(carbon.getCarbon6());
            CARBON.add(carbon.getCarbon7());
            gas.setCarbon(CARBON);
            log.info("查询气体成功");
            return new Result<Gas>(Code.OK, "查询气体成功", gas);
        } catch (Exception e) {
            log.info("系统出现异常");
            e.printStackTrace();
            return new Result<>(Code.EXCEPTION, "系统出现异常", null);
        }
    }


}
