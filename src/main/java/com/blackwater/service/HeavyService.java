package com.blackwater.service;

import com.blackwater.entity.Heavy.*;
import com.blackwater.entity.HeavyContent.HeavyContent;
import com.blackwater.config.until.Result;

import java.util.List;

public interface HeavyService {

    /**
     * 查重金属超标接口
     *
     * @return
     */
    Result<List<Mental>> selectMentalData();

    /**
     * 查酸碱度超标接口
     *
     * @return
     */
    Result<List<Ph>> selectPhData();

    /**
     * 查微生物超标接口
     *
     * @return
     */
    Result<List<Organism>> selectOrganismData();

    /**
     * 查垃圾超标接口
     *
     * @return
     */
    Result<List<Garbage>> selectGarbageData();


    /**
     * 查放射元素超标接口
     *
     * @return
     */
    Result<List<Radiation>> selectRadiationData();

    /**
     * 删除超标接口
     *
     * @return
     */
    Result<String> deleteById(int id, String flag);

    /**
     * 查询超标含量(5天)
     *
     * @return
     */
    Result<HeavyContent> selectHeavyContent();
}
