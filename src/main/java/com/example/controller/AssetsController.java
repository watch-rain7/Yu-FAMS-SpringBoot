package com.example.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Assets;
import com.example.service.AssetsService;
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
 * 资产信息前端操作接口
 **/
@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Resource
    private AssetsService assetsService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Assets assets) {
        assetsService.add(assets);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Assets assets) {
        assetsService.updateById(assets);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Assets assets = assetsService.selectById(id);
        return Result.success(assets);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Assets assets) {
        List<Assets> list = assetsService.selectAll(assets);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Assets assets,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Assets> page = assetsService.selectPage(assets, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 批量导出数据
     */
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        List<Assets> list = assetsService.selectAll(null);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产信息表", "UTF-8") + ".xlsx");
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
        Assets template = new Assets();
        template.setName("示例资产");
        template.setCategory("办公设备");
        template.setNo("ZC001");
        template.setModel("型号示例");
        template.setNum(1);
        template.setDate("2024-01-01");
        template.setMoney(new java.math.BigDecimal("1000.00"));
        template.setDepreciate("直线法");
        template.setLocation("办公室");
        template.setStatus("正常");
        template.setComment("备注示例");

        List<Assets> templateList = java.util.Arrays.asList(template);
        writer.write(templateList, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产导入模板", "UTF-8") + ".xlsx");
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
            List<Assets> list = ExcelUtil.getReader(file.getInputStream()).readAll(Assets.class);
            if (list != null && !list.isEmpty()) {
                for (Assets assets : list) {
                    // 设置默认值
                    if (assets.getStatus() == null) {
                        assets.setStatus("正常");
                    }
                    if (assets.getNum() == null) {
                        assets.setNum(1);
                    }
                    assetsService.add(assets);
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