package com.blackwater.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackwater.entity.Turbidity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface TurbidityDao extends BaseMapper<com.blackwater.entity.Turbidity> {

    void addTurbidity(Turbidity turbidity);



}
