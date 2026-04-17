package com.blackwater.phoneService.phoneImpl;

import com.blackwater.dao.daoPhone.DataDao;
import com.blackwater.entity.phone.Fault;
import com.blackwater.entity.phone.MapData;
import com.blackwater.entity.phone.News;
import com.blackwater.entity.phone.PhoneData;
import com.blackwater.phoneService.DataService;
import com.blackwater.config.until.Code;
import com.blackwater.config.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;
    @Override
    public Result<List<PhoneData>> selectAllData() {

        try {
            List<PhoneData> data = dataDao.selectAllData();
//            log.info("查询成功");
            return new Result<List<PhoneData>>(Code.OK,"查询成功",data);
        } catch (Exception e) {
            log.error("系统出现异常");
            e.printStackTrace();
            return new Result<>(Code.ERR,"系统出现异常",null);
        }
    }


    @Override
    public Result<String> addFault(Fault fault) {
        dataDao.addFault(fault);
        return new Result<>(Code.OK,"添加成功",null);
    }

    @Override
    public Result<News> selectNews() {
        News news = dataDao.selectNewsByRandom();
        return new Result<>(Code.OK,"查询成功",news);
    }

    @Override
    public  Result<List<MapData>> selectMap() {

        List<MapData> mapData = dataDao.selectMap();

        return new Result<>(Code.OK,"查询成功",mapData);
    }
}
