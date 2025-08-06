package com.example.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.AssetsDetail;
import com.example.service.AssetsDetailService;
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
 * 资产详情记录前端操作接口
 **/
@RestController
@RequestMapping("/assetsDetail")
public class AssetsDetailController {

    @Resource
    private AssetsDetailService assetsDetailService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody AssetsDetail assetsDetail) {
        assetsDetailService.add(assetsDetail);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsDetailService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsDetailService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AssetsDetail assetsDetail) {
        assetsDetailService.updateById(assetsDetail);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        AssetsDetail assetsDetail = assetsDetailService.selectById(id);
        return Result.success(assetsDetail);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AssetsDetail assetsDetail) {
        List<AssetsDetail> list = assetsDetailService.selectAll(assetsDetail);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(AssetsDetail assetsDetail,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AssetsDetail> page = assetsDetailService.selectPage(assetsDetail, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 根据资产ID查询详情记录
     */
    @GetMapping("/selectByAssetsId/{assetsId}")
    public Result selectByAssetsId(@PathVariable Integer assetsId) {
        List<AssetsDetail> list = assetsDetailService.selectByAssetsId(assetsId);
        return Result.success(list);
    }

    /**
     * 根据资产ID和操作类型查询记录
     */
    @GetMapping("/selectByAssetsIdAndType")
    public Result selectByAssetsIdAndType(@RequestParam Integer assetsId, 
                                         @RequestParam String operationType) {
        List<AssetsDetail> list = assetsDetailService.selectByAssetsIdAndType(assetsId, operationType);
        return Result.success(list);
    }

    /**
     * 记录资产操作
     */
    @PostMapping("/recordOperation")
    public Result recordOperation(@RequestParam Integer assetsId,
                                 @RequestParam String operationType,
                                 @RequestParam String description,
                                 @RequestParam(required = false) String comment) {
        assetsDetailService.recordOperation(assetsId, operationType, description, comment);
        return Result.success();
    }

    /**
     * 批量导出数据
     */
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        List<AssetsDetail> list = assetsDetailService.selectAll(null);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产详情记录表", "UTF-8") + ".xlsx");
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
        AssetsDetail template = new AssetsDetail();
        template.setAssetsName("示例资产");
        template.setAssetsNo("ZC001");
        template.setOperationType("入库");
        template.setOperationTime("2024-01-01 10:00:00");
        template.setOperatorName("操作人");
        template.setDescription("操作描述");
        template.setComment("备注示例");

        List<AssetsDetail> templateList = java.util.Arrays.asList(template);
        writer.write(templateList, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产详情记录导入模板", "UTF-8") + ".xlsx");
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
            List<AssetsDetail> list = ExcelUtil.getReader(file.getInputStream()).readAll(AssetsDetail.class);
            if (list != null && !list.isEmpty()) {
                for (AssetsDetail assetsDetail : list) {
                    assetsDetailService.add(assetsDetail);
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