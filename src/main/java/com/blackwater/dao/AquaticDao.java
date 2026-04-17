package com.blackwater.dao;

import com.blackwater.entity.Aquatic.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AquaticDao {

    List<DissolvedSalt> selectDissolvedSalt();

    List<Soluble> selectSoluble();

    List<Mental> selectMental();

    List<Organism> selectOrganism();

    List<Radiation> selectRadiation();

    int  deleteDissolvedSalt(String site);

    int deleteOrganism(String site);
    int deleteMental(String site);
    int deleteSoluble(String site);
    int deleteRadiation(String site);

}
