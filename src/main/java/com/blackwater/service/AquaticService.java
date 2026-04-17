package com.blackwater.service;

import com.blackwater.config.until.Result;

public interface AquaticService<T> {
     /**
      * 查询水溶物
      * @param id
      * @return
      */
     Result<T> selectDissolvedSalt(int id);

     /**
      * 删除水溶物
      * @param site
      * @param flag
      * @return
      */
     Result<T> deleteAquatic(String site,String flag);
}
