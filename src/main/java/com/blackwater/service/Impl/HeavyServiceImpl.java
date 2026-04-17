package com.blackwater.service.Impl;

import com.blackwater.dao.HeavyDao;
import com.blackwater.entity.Heavy.*;
import com.blackwater.entity.HeavyContent.HeavyContent;
import com.blackwater.service.HeavyService;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HeavyServiceImpl implements HeavyService {

    @Autowired
    private HeavyDao heavyDao;

    /**
     * 查重金属超标接口
     *
     * @return
     */
    @Override
    public Result<List<Mental>> selectMentalData() {

        try {
            List<Mental> mentals = heavyDao.selectMentalData();
            log.info("查重金属成功");
            return new Result<List<Mental>>(Code.OK, "查重金属成功", mentals);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 查酸碱度超标接口
     *
     * @return
     */
    @Override
    public Result<List<Ph>> selectPhData() {
        try {
            List<Ph> phs = heavyDao.selectPhData();
            log.info("查酸碱度成功");
            return new Result<List<Ph>>(Code.OK, "查酸碱度成功", phs);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 查微生物超标接口
     *
     * @return
     */
    @Override
    public Result<List<Organism>> selectOrganismData() {
        try {
            List<Organism> organisms = heavyDao.selectOrganismData();
            log.info("查微生物成功");
            return new Result<List<Organism>>(Code.OK, "查微生物成功", organisms);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 查垃圾超标接口
     *
     * @return
     */
    @Override
    public Result<List<Garbage>> selectGarbageData() {
        try {
            List<Garbage> garbage = heavyDao.selectGarbageData();
            log.info("查垃圾成功");
            return new Result<List<Garbage>>(Code.OK, "查垃圾成功", garbage);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 查放射元素超标接口
     *
     * @return
     */
    @Override
    public Result<List<Radiation>> selectRadiationData() {
        try {
            List<Radiation> radiation = heavyDao.selectRadiationData();
            log.info("查放射元素成功");
            return new Result<List<Radiation>>(Code.OK, "查放射元素成功", radiation);
        } catch (Exception e) {
            log.error("系统异常");
            e.printStackTrace();
            return new Result<>(Code.ERR, "系统异常", null);
        }
    }

    /**
     * 删除超标
     *
     * @return
     */
    @Override
    public Result<String> deleteById(int id, String flag) {
        log.info("传入的id:{},flag:{}", id, flag);
        switch (flag) {
            case "重金属":
                heavyDao.deleteMentalById(id);
                log.info("删除重金属成功");
                return new Result<>(Code.OK, "删除重金属成功");
            case "垃圾":
                heavyDao.deleteGarbageById(id);
                log.info("删除垃圾成功");
                return new Result<>(Code.OK, "删除垃圾成功");

            case "放射":
                heavyDao.deleteRadiationById(id);
                log.info("删除放射成功");
                return new Result<>(Code.OK, "删除放射成功");

            case "酸碱":
                heavyDao.deletePhById(id);
                log.info("删除酸碱成功");
                return new Result<>(Code.OK, "删除酸碱成功");
            case "微生物":
                heavyDao.deleteOrganismById(id);
                log.info("删除微生物成功");
                return new Result<>(Code.OK, "删除微生物成功");
            default:
                log.error("系统异常");
                return new Result<>(Code.ERR, "系统异常", null);
        }

    }

    /**
     * 查询超标含量(5天)
     *
     * @return
     */
    @Override
    public Result<HeavyContent> selectHeavyContent() {
        HeavyContent heavyContent = new HeavyContent();
        try {
            //查询重金属content
            ArrayList<Double> MENTAL = new ArrayList<>();
            com.blackwater.entity.HeavyContent.Mental mental = heavyDao.selectMentalContent();
            MENTAL.add(mental.getContent1());
            MENTAL.add(mental.getContent2());
            MENTAL.add(mental.getContent3());
            MENTAL.add(mental.getContent4());
            MENTAL.add(mental.getContent5());
            heavyContent.setMental(MENTAL);
            //查询垃圾content
            ArrayList<Double> GARBAGE = new ArrayList<>();
            com.blackwater.entity.HeavyContent.Garbage garbage = heavyDao.selectGarbageContent();
            GARBAGE.add(garbage.getContent1());
            GARBAGE.add(garbage.getContent2());
            GARBAGE.add(garbage.getContent3());
            GARBAGE.add(garbage.getContent4());
            GARBAGE.add(garbage.getContent5());
            heavyContent.setGarbage(GARBAGE);
            //查询放射content
            ArrayList<Double> RADIATION = new ArrayList<>();
            com.blackwater.entity.HeavyContent.Radiation radiation = heavyDao.selectRadiationContent();
            RADIATION.add(radiation.getContent1());
            RADIATION.add(radiation.getContent2());
            RADIATION.add(radiation.getContent3());
            RADIATION.add(radiation.getContent4());
            RADIATION.add(radiation.getContent5());
            heavyContent.setRadiation(RADIATION);
            //查询微生物content
            ArrayList<Double> ORGANIC = new ArrayList<>();
            com.blackwater.entity.HeavyContent.Organism organism = heavyDao.selectOrganismContent();
            ORGANIC.add(organism.getContent1());
            ORGANIC.add(organism.getContent2());
            ORGANIC.add(organism.getContent3());
            ORGANIC.add(organism.getContent4());
            ORGANIC.add(organism.getContent5());
            heavyContent.setOrganism(ORGANIC);
            //返回结果
            log.info("查询超标含量成功");
            return new Result<HeavyContent>(Code.OK,"查询超标含量成功",heavyContent);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("系统出现异常");
            return new Result<>(Code.EXCEPTION, "系统出现异常");
        }
    }
}
