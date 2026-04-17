package com.blackwater.controller;

import com.blackwater.entity.DissolvedOxygen;
import com.blackwater.entity.ElementAndDissolve.ElementAndDissolve;
import com.blackwater.service.DissolvedOxygenService;
import com.blackwater.config.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "溶解氧")
@RequestMapping("/dissolvedOxygen")
public class DissolvedOxygenController {
    @Autowired
    private DissolvedOxygenService dissolvedOxygenService;

    /**
     * 查询元素和溶解氧
     * @return
     */
    @PostMapping("/select/element/and/dissolve")
    @ApiOperation(value = "查询元素和溶解氧", notes = "查询元素和溶解氧 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询元素和溶解氧 成功状态码")
    })
    public Result<ElementAndDissolve> selectElementAndDissolve() {
        return dissolvedOxygenService.selectElementAndDissolve();
    }


    @PostMapping("/add")
    public String addDO(@RequestBody DissolvedOxygen dissolvedOxygen) {
        return dissolvedOxygenService.addDO(dissolvedOxygen);
    }




}
