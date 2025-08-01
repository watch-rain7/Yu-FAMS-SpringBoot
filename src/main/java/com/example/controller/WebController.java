package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Category;
import com.example.entity.Staff;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.AssetsService;
import com.example.service.CategoryService;
import com.example.service.StaffService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础前端接口
 */
@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    private StaffService staffService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private AssetsService assetsService;
    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        } else if (RoleEnum.STAFF.name().equals(account.getRole())) {
            account = staffService.login(account);
        }
        return Result.success(account);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.STAFF.name().equals(account.getRole())) {
            staffService.register(account);
        } else {
            return Result.error("500", "只允许注册员工");
        }
        return Result.success();
    }


    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }else if (RoleEnum.STAFF.name().equals(account.getRole())) {
            staffService.updatePassword(account);
        }
        return Result.success();
    }

    /**
     * 分类资产的数据统计
     */
    @GetMapping("/selectPie")
    public Result selectPie() {
        List<Category> categoryList = categoryService.selectAll(null);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Category category : categoryList) {
            int count = assetsService.selectCountByCategory(category.getName());
            if(count==0){
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("name", category.getName());
            map.put("value", count);
            list.add(map);
        }
        return Result.success(list);
    }

}
