package com.blackwater.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.blackwater.entity.Turbidity;
import com.blackwater.config.until.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface TurbidityService extends IService<Turbidity> {


    /**
     * 查询浊度接口
     * @return
     */
    Result<List<Turbidity>> selectTurbidityData();

    String addTurbidity(Turbidity turbidity);





}
