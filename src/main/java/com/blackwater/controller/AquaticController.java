package com.blackwater.controller;

import com.blackwater.service.AquaticService;
import com.blackwater.config.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "水溶物")
@RequestMapping("/aquatic")
public class AquaticController {
    @Autowired
    private AquaticService aquaticService;
    /**
     * 查询水溶物
     * @param id
     * @return
     */
    @GetMapping("/select")
    @ApiOperation(value = "查询水溶物", notes = "查询水溶物 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询水溶物 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result selectAquatic(@RequestParam("id") int id) {
        return aquaticService.selectDissolvedSalt(id);
    }
    /**
     * 删除水溶物
     * @param site
     * @param flag
     * @return
     */
    @GetMapping("/delete")
    @ApiOperation(value = "删除水溶物", notes = "删除水溶物 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询水溶物 成功状态码"),
            @ApiResponse(code = 0, message = "系 统异常")
    })
    public Result deleteAquatic(@RequestParam("site") String site,@RequestParam("flag") String flag) {
        return aquaticService.deleteAquatic(site,flag);
    }

}
