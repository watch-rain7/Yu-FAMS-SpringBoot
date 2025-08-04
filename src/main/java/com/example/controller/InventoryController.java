package com.example.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Inventory;
import com.example.service.InventoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 资产盘点前端操作接口
 **/
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Resource
    private InventoryService inventoryService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Inventory inventory) {
        inventoryService.add(inventory);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        inventoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        inventoryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Inventory inventory) {
        inventoryService.updateById(inventory);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Inventory inventory = inventoryService.selectById(id);
        return Result.success(inventory);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Inventory inventory) {
        List<Inventory> list = inventoryService.selectAll(inventory);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Inventory inventory,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Inventory> page = inventoryService.selectPage(inventory, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 获取最近的盘点记录
     */
    @GetMapping("/recent")
    public Result getRecentInventory(@RequestParam(defaultValue = "10") Integer limit) {
        List<Inventory> list = inventoryService.getRecentInventory(limit);
        return Result.success(list);
    }

    /**
     * 批量导出数据
     */
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        List<Inventory> list = inventoryService.selectAll(null);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产盘点表", "UTF-8") + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.flush();
        writer.close();
        outputStream.close();
    }

    /**
     * 下载导入模板
     */
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 创建一个示例数据作为模板
        Inventory template = new Inventory();
        template.setAssetsName("示例资产");
        template.setAssetsNo("ZC001");
        template.setInventoryTime("2024-01-01 10:00:00");
        template.setStaffName("盘点人");
        template.setResult("正常");
        template.setStatus("已盘点");
        template.setLocation("办公室");
        template.setComment("备注示例");

        List<Inventory> templateList = java.util.Arrays.asList(template);
        writer.write(templateList, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产盘点导入模板", "UTF-8") + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.flush();
        writer.close();
        outputStream.close();
    }

    /**
     * 批量导入数据
     */
    @PostMapping("/import")
    public Result importData(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return Result.error("500", "请选择要上传的文件");
        }
        
        try {
            List<Inventory> list = ExcelUtil.getReader(file.getInputStream()).readAll(Inventory.class);
            if (list != null && !list.isEmpty()) {
                for (Inventory inventory : list) {
                    // 设置默认值
                    if (inventory.getStatus() == null) {
                        inventory.setStatus("待盘点");
                    }
                    if (inventory.getResult() == null) {
                        inventory.setResult("正常");
                    }
                    inventoryService.add(inventory);
                }
                return Result.success("成功导入 " + list.size() + " 条数据");
            } else {
                return Result.error("500", "文件中没有数据");
            }
        } catch (Exception e) {
            return Result.error("500", "导入失败：" + e.getMessage());
        }
    }
} 