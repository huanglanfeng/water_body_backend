package com.blackwater.dao.daoPhone;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackwater.entity.phone.Fault;
import com.blackwater.entity.phone.MapData;
import com.blackwater.entity.phone.PhoneData;
import org.springframework.stereotype.Repository;
import com.blackwater.entity.phone.News;

import java.util.List;

@Repository
public interface DataDao extends BaseMapper<PhoneData> {
    /**
     * 手机端所有信息
     *
     * @return
     */
    List<PhoneData> selectAllData();

    /**
     * 添加故障信息
     *
     * @return
     */
    void addFault(Fault fault);

    /**
     * 随机查询一条新闻
     * @return
     */
    News selectNewsByRandom();

    /**
     * 查询地图信息
     * @return
     */
    List<MapData> selectMap();


}
