package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AssetsReceive;
import com.example.mapper.AssetsReceiveMapper;
import com.example.service.AssetsReceiveService;
import com.example.service.AssetsService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 资产领用前端操作接口
 **/
@RestController
@RequestMapping("/assetsReceive")
public class AssetsReceiveController {

    @Resource
    private AssetsReceiveService assetsReceiveService;
    @Resource
    private AssetsService assetsService;
    @Resource
    private AssetsReceiveMapper assetsReceiveMapper;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody AssetsReceive assetsReceive) {

        assetsReceiveService.add(assetsReceive);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsReceiveService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsReceiveService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AssetsReceive assetsReceive) {
        assetsReceiveService.updateById(assetsReceive);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        AssetsReceive assetsReceive = assetsReceiveService.selectById(id);
        return Result.success(assetsReceive);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AssetsReceive assetsReceive) {
        List<AssetsReceive> list = assetsReceiveService.selectAll(assetsReceive);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(AssetsReceive assetsReceive,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AssetsReceive> page = assetsReceiveService.selectPage(assetsReceive, pageNum, pageSize);
        return Result.success(page);
    }

}