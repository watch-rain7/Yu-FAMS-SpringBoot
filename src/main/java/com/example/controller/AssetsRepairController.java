package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Assets;
import com.example.entity.AssetsReceive;
import com.example.entity.AssetsRepair;
import com.example.exception.CustomException;
import com.example.mapper.AssetsReceiveMapper;
import com.example.mapper.AssetsRepairMapper;
import com.example.service.AssetsRepairService;
import com.example.service.AssetsService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 资产报修前端操作接口
 **/
@RestController
@RequestMapping("/assetsRepair")
public class AssetsRepairController {
//
//    @Resource
//    private AssetsRepairService assetsRepairService;

    @Autowired
    private AssetsRepairService assetsRepairService;


    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody (required=false) AssetsRepair assetsRepair) {

        assetsRepairService.add(assetsRepair);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsRepairService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsRepairService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AssetsRepair assetsRepair) {
        assetsRepairService.updateById(assetsRepair);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        AssetsRepair assetsRepair = assetsRepairService.selectById(id);
        return Result.success(assetsRepair);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AssetsRepair assetsRepair) {
        List<AssetsRepair> list = assetsRepairService.selectAll(assetsRepair);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(AssetsRepair assetsRepair,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AssetsRepair> page = assetsRepairService.selectPage(assetsRepair, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/recent")
    public Result getRecentRepairs() {
        return Result.success(assetsRepairService.getRecentRepairs());
    }

}