package com.blackwater.phoneService;

import com.blackwater.entity.phone.Fault;
import com.blackwater.entity.phone.MapData;
import com.blackwater.entity.phone.News;
import com.blackwater.entity.phone.PhoneData;
import com.blackwater.config.until.Result;

import java.util.List;


public interface DataService {

   /**
    *  查询所有信息(手机)
    * @return
    */
   Result<List<PhoneData>> selectAllData();

   /**
    * 添加故障信息(手机)
    * @return
    */
   Result<String> addFault(Fault fault);

   /**
    * 随机查询一条新闻(手机)
    * @return
    */
   Result<News> selectNews();

   Result<List<MapData>> selectMap();


}
