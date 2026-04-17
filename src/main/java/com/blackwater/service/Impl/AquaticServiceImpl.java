package com.blackwater.service.Impl;

import com.blackwater.dao.AquaticDao;
import com.blackwater.entity.Aquatic.*;
import com.blackwater.service.AquaticService;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AquaticServiceImpl implements AquaticService {
    @Autowired
    private AquaticDao aquaticDao;

    /**
     * 查询水溶物
     * @param id
     * @return
     */
    @Override
    public Result selectDissolvedSalt(int id) {
        log.info("传入的number:{}",id);
        switch (id) {
            case 1:
                List<DissolvedSalt> dissolvedSalts = aquaticDao.selectDissolvedSalt();
                log.info("查询溶解盐成功");
                return new Result<List<DissolvedSalt>>(Code.OK, "查询溶解盐成功",dissolvedSalts);
            case 2:
                List<Soluble> solubles = aquaticDao.selectSoluble();
                log.info("查询水溶物成功");
                return new Result<List<Soluble>>(Code.OK, "查询水溶物成功",solubles);
            case 3:
                List<Mental> mentals = aquaticDao.selectMental();
                log.info("查询重金属成功");
                return new Result<List<Mental>>(Code.OK, "查询重金属成功",mentals);
            case 4:
                List<Organism> organisms = aquaticDao.selectOrganism();
                log.info("查询微生物成功");
                return new Result<List<Organism>>(Code.OK, "查询微生物成功",organisms);
            case 5:
                List<Radiation> radiations = aquaticDao.selectRadiation();
                log.info("查询放射物成功");
                return new Result<List<Radiation>>(Code.OK, "查询放射物成功",radiations);
            default:
                log.error("系统异常");
                return new Result<>(Code.ERR, "系统异常", null);
        }
    }
    /**
     * 删除水溶物
     * @param site
     * @param flag
     * @return
     */
    @Override
    public Result deleteAquatic(String site, String flag) {
        log.info("传入的site:{},传入的flag:{}",site,flag);
        switch (flag) {
            case "溶解盐":
                aquaticDao.deleteDissolvedSalt(site);
                log.info("删除溶解盐成功");
                return new Result<>(Code.OK, "删除溶解盐成功");
            case "水溶物":
                aquaticDao.deleteSoluble(site);
                log.info("删除水溶物成功");
                return new Result<>(Code.OK, "删除水溶物成功");
            case "重金属":
                aquaticDao.deleteMental(site);
                log.info("删除重金属成功");
                return new Result<>(Code.OK, "删除重金属成功");
            case "微生物":
                aquaticDao.deleteOrganism(site);
                log.info("删除微生物成功");
                return new Result<>(Code.OK, "删除微生物成功");
            case "放射物":
                aquaticDao.deleteRadiation(site);
                log.info("删除放射物成功");
                return new Result<>(Code.OK, "删除放射物成功");
            default:
                log.error("系统异常");
                return new Result<>(Code.ERR, "系统异常", null);
        }
    }

}
