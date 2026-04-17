package com.blackwater.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackwater.dao.TurbidityDao;
import com.blackwater.entity.Turbidity;
import com.blackwater.service.TurbidityService;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TurbidityServiceImpl extends ServiceImpl<TurbidityDao, Turbidity> implements TurbidityService {

    @Autowired
    private TurbidityDao turbidityDao;

    /**
     * 查询浊度接口
     *
     * @return
     */
    @Override
    public Result<List<Turbidity>> selectTurbidityData() {
        try {
            QueryWrapper<Turbidity> qw = new QueryWrapper<>();
            List<Turbidity> turbidities = turbidityDao.selectList(qw);
            log.info("查浊成功");
            return new Result<List<Turbidity>>(Code.OK, "查浊成功", turbidities);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出现异常");
            return new Result<>(Code.EXCEPTION, "系统出现异常");
        }
    }

    @Override
    public String addTurbidity(Turbidity turbidity) {
        try {
            turbidityDao.addTurbidity(turbidity);
            return "1 添加成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "0 添加失败";
        }
    }
}
