package com.example.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.AssetsTransfer;
import com.example.service.AssetsTransferService;
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
 * 资产调拨前端操作接口
 **/
@RestController
@RequestMapping("/assetsTransfer")
public class AssetsTransferController {

    @Resource
    private AssetsTransferService assetsTransferService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody AssetsTransfer assetsTransfer) {
        assetsTransferService.add(assetsTransfer);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsTransferService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsTransferService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AssetsTransfer assetsTransfer) {
        assetsTransferService.updateById(assetsTransfer);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        AssetsTransfer assetsTransfer = assetsTransferService.selectById(id);
        return Result.success(assetsTransfer);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AssetsTransfer assetsTransfer) {
        List<AssetsTransfer> list = assetsTransferService.selectAll(assetsTransfer);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(AssetsTransfer assetsTransfer,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AssetsTransfer> page = assetsTransferService.selectPage(assetsTransfer, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 根据资产ID查询调拨记录
     */
    @GetMapping("/selectByAssetsId/{assetsId}")
    public Result selectByAssetsId(@PathVariable Integer assetsId) {
        List<AssetsTransfer> list = assetsTransferService.selectByAssetsId(assetsId);
        return Result.success(list);
    }

    /**
     * 审批调拨申请
     */
    @PostMapping("/approve")
    public Result approve(@RequestParam Integer id, 
                         @RequestParam String status, 
                         @RequestParam(required = false) String approveComment) {
        assetsTransferService.approve(id, status, approveComment);
        return Result.success();
    }

    /**
     * 执行调拨
     */
    @PostMapping("/execute/{id}")
    public Result executeTransfer(@PathVariable Integer id) {
        assetsTransferService.executeTransfer(id);
        return Result.success();
    }

    /**
     * 批量导出数据
     */
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        List<AssetsTransfer> list = assetsTransferService.selectAll(null);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产调拨表", "UTF-8") + ".xlsx");
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
        AssetsTransfer template = new AssetsTransfer();
        template.setAssetsName("示例资产");
        template.setAssetsNo("ZC001");
        template.setTransferTime("2024-01-01 10:00:00");
        template.setTransferStaffName("调拨人");
        template.setFromStaffName("原使用人");
        template.setFromDepartmentName("原部门");
        template.setToStaffName("新使用人");
        template.setToDepartmentName("新部门");
        template.setReason("调拨原因");
        template.setStatus("待审批");
        template.setComment("备注示例");

        List<AssetsTransfer> templateList = java.util.Arrays.asList(template);
        writer.write(templateList, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产调拨导入模板", "UTF-8") + ".xlsx");
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
            List<AssetsTransfer> list = ExcelUtil.getReader(file.getInputStream()).readAll(AssetsTransfer.class);
            if (list != null && !list.isEmpty()) {
                for (AssetsTransfer assetsTransfer : list) {
                    // 设置默认值
                    if (assetsTransfer.getStatus() == null) {
                        assetsTransfer.setStatus("待审批");
                    }
                    assetsTransferService.add(assetsTransfer);
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