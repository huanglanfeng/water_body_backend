package com.blackwater.dao;

import com.blackwater.entity.Heavy.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeavyDao {
    /**
     * 查重金属超标
     *
     * @return
     */
    List<Mental> selectMentalData();

    /**
     * 查酸碱度超标
     *
     * @return
     */
    List<Ph> selectPhData();

    /**
     * 查微生物超标
     *
     * @return
     */
    List<Organism> selectOrganismData();

    /**
     * 查垃圾超标
     *
     * @return
     */
    List<Garbage> selectGarbageData();

    /**
     * 查放射元素超标
     *
     * @return
     */
    List<Radiation> selectRadiationData();


    int deleteMentalById(int id);

    int deleteGarbageById(int id);

    int deleteOrganismById(int id);

    int deletePhById(int id);

    int deleteRadiationById(int id);

    com.blackwater.entity.HeavyContent.Garbage selectGarbageContent();
    com.blackwater.entity.HeavyContent.Mental selectMentalContent();
    com.blackwater.entity.HeavyContent.Organism selectOrganismContent();
    com.blackwater.entity.HeavyContent.Radiation selectRadiationContent();


}
