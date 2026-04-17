package com.blackwater.controller;

import com.blackwater.entity.Gas.Gas;
import com.blackwater.entity.GasUseless;
import com.blackwater.config.until.Result;
import com.blackwater.service.GasService;
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
@Api(tags = "气体")
@RequestMapping("/gas")
public class GasController {

    @Autowired
    private GasService gasService;

    /**
     * 添加气体浓度接口
     *
     * @param gas 包含（氨气、一氧化氮 等）
     * @return 返回是否添加成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加气体浓度", notes = "添加气体浓度 接口")
    @ApiResponses({
            @ApiResponse(code = 30001, message = "添加气体  成功状态码"),
            @ApiResponse(code = 30000, message = "添加气体  失败状态码")
    })
    public Result<String> addGas(@RequestBody GasUseless gas) {
        return gasService.addGas(gas);
    }

    /**
     * 根据地区删除气体浓度数据接口
     *
     * @param address 地区
     * @return 返回是否删除成功
     */
    @PostMapping("/delete/by/address")
    @ApiOperation(value = "删除气体浓度 根据地址", notes = "删除气体浓度 根据地址 接口")
    @ApiResponses({
            @ApiResponse(code = 20001, message = "删除气体浓度根据地址  成功状态码"),
            @ApiResponse(code = 20000, message = "删除气体浓度根据地址  失败状态码")
    })
    public Result<String> deleteGasByAddress(String address) {
        return gasService.deleteGasByAddress(address);
    }

    @PostMapping("/update/by/id")
//    @ApiOperation(value = "修改气体数据 根据id", notes = "修改气体数据 根据id 接口")
    @ApiResponses({
            @ApiResponse(code = 40001, message = "修改气体数据 根据id  成功状态码"),
            @ApiResponse(code = 40000, message = "修改气体数据 根据id  失败状态码"),
            @ApiResponse(code = 50000, message = "系统出现异常 状态码")
    })
    public Result<String> modifyById(int id) {
        return gasService.modifyById(id);
    }


    /**
     * 查询气体(7天)
     *
     * @return
     */
    @PostMapping("/select/gas")
    @ApiOperation(value = "查询气体", notes = "查询气体 接口")
    @ApiResponses({
            @ApiResponse(code = 20001, message = "查询气体  成功状态码"),
    })
    public Result<Gas> selectGas() {
        return gasService.selectGas();
    }


}
