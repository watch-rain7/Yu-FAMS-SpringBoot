package com.example.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.ApiConfig;
import com.example.service.ApiConfigService;
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
 * 接口配置前端操作接口
 **/
@RestController
@RequestMapping("/apiConfig")
public class ApiConfigController {

    @Resource
    private ApiConfigService apiConfigService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody ApiConfig apiConfig) {
        apiConfigService.add(apiConfig);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        apiConfigService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        apiConfigService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody ApiConfig apiConfig) {
        apiConfigService.updateById(apiConfig);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ApiConfig apiConfig = apiConfigService.selectById(id);
        return Result.success(apiConfig);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(ApiConfig apiConfig) {
        List<ApiConfig> list = apiConfigService.selectAll(apiConfig);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(ApiConfig apiConfig,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ApiConfig> page = apiConfigService.selectPage(apiConfig, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 根据状态查询
     */
    @GetMapping("/selectByStatus/{status}")
    public Result selectByStatus(@PathVariable String status) {
        List<ApiConfig> list = apiConfigService.selectByStatus(status);
        return Result.success(list);
    }

    /**
     * 根据类型查询
     */
    @GetMapping("/selectByType/{type}")
    public Result selectByType(@PathVariable String type) {
        List<ApiConfig> list = apiConfigService.selectByType(type);
        return Result.success(list);
    }

    /**
     * 测试接口
     */
    @PostMapping("/test/{id}")
    public Result testApi(@PathVariable Integer id) {
        String result = apiConfigService.testApi(id);
        return Result.success(result);
    }

    /**
     * 批量导出数据
     */
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        List<ApiConfig> list = apiConfigService.selectAll(null);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("接口配置表", "UTF-8") + ".xlsx");
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
        ApiConfig template = new ApiConfig();
        template.setName("示例接口");
        template.setDescription("接口描述");
        template.setUrl("http://example.com/api");
        template.setMethod("GET");
        template.setHeaders("{\"Content-Type\": \"application/json\"}");
        template.setParameters("{\"param1\": \"value1\"}");
        template.setResponseFormat("JSON");
        template.setStatus("启用");
        template.setType("内部接口");
        template.setAuthType("无认证");
        template.setAuthInfo("{}");
        template.setTimeout(30);
        template.setRetryCount(0);

        List<ApiConfig> templateList = java.util.Arrays.asList(template);
        writer.write(templateList, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("接口配置导入模板", "UTF-8") + ".xlsx");
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
            List<ApiConfig> list = ExcelUtil.getReader(file.getInputStream()).readAll(ApiConfig.class);
            if (list != null && !list.isEmpty()) {
                for (ApiConfig apiConfig : list) {
                    // 设置默认值
                    if (apiConfig.getMethod() == null) {
                        apiConfig.setMethod("GET");
                    }
                    if (apiConfig.getResponseFormat() == null) {
                        apiConfig.setResponseFormat("JSON");
                    }
                    if (apiConfig.getStatus() == null) {
                        apiConfig.setStatus("启用");
                    }
                    if (apiConfig.getType() == null) {
                        apiConfig.setType("内部接口");
                    }
                    if (apiConfig.getAuthType() == null) {
                        apiConfig.setAuthType("无认证");
                    }
                    if (apiConfig.getTimeout() == null) {
                        apiConfig.setTimeout(30);
                    }
                    if (apiConfig.getRetryCount() == null) {
                        apiConfig.setRetryCount(0);
                    }
                    apiConfigService.add(apiConfig);
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