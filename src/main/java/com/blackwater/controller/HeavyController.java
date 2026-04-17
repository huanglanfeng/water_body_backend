package com.blackwater.controller;

import com.blackwater.entity.Heavy.*;
import com.blackwater.entity.HeavyContent.HeavyContent;
import com.blackwater.service.HeavyService;
import com.blackwater.config.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "超标信息")
@RequestMapping("/heavy")
public class HeavyController {


    @Autowired
    private HeavyService heavyService;

    /**
     * 查重金属超标
     *
     * @return
     */
    @PostMapping("/select/mental")
    @ApiOperation(value = "查重金属超标", notes = "查重金属超标 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查重金属超标 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Mental>> selectMentalData() {
        return heavyService.selectMentalData();
    }

    /**
     * 查酸碱度超标
     *
     * @return
     */
    @PostMapping("/select/ph")
    @ApiOperation(value = "查酸碱度超标", notes = "查酸碱度超标 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查酸碱度超标 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Ph>> selectPhData() {
        return heavyService.selectPhData();
    }

    /**
     * 查微生物超标
     *
     * @return
     */
    @PostMapping("/select/organism")
    @ApiOperation(value = "查微生物超标", notes = "查微生物超标 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查微生物超标 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Organism>> selectOrganismData() {
        return heavyService.selectOrganismData();
    }

    /**
     * 查垃圾超标接口
     *
     * @return
     */
    @PostMapping("/select/garbage")
    @ApiOperation(value = "查垃圾超标", notes = "查垃圾超标 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查垃圾超标 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Garbage>> selectGarbageData() {
        return heavyService.selectGarbageData();
    }

    /**
     * 查放射元素超标接口
     *
     * @return
     */
    @PostMapping("/select/radiation")
    @ApiOperation(value = "查放射元素超标", notes = "查放射元素超标 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查放射元素超标 成功状态码"),
            @ApiResponse(code = 0, message = "系统异常")
    })
    public Result<List<Radiation>> selectRadiationData() {
        return heavyService.selectRadiationData();
    }

    /**
     * 删除超标
     *
     * @return
     */
    @GetMapping("/delete/by/id")
    @ApiOperation(value = "删除超标", notes = "删除超标 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "删除超标 成功状态码"),
            @ApiResponse(code = 0, message = "删除超标 失败状态码")
    })
    public Result<String> deleteById(@RequestParam("id") int id,@RequestParam("flag") String flag) {
        return heavyService.deleteById(id, flag);
    }


    /**
     * 查询超标含量(5天)
     *
     * @return
     */
    @PostMapping("/select/heavy/content")
    @ApiOperation(value = "查询超标含量", notes = "查询超标含量 接口")
    @ApiResponses({
            @ApiResponse(code = 1, message = "查询超标含量 成功状态码"),
    })
    public Result<HeavyContent> selectHeavyContent() {
        return heavyService.selectHeavyContent();
    }


}
