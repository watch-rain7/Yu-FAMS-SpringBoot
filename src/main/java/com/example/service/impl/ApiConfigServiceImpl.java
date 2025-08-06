package com.example.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.example.entity.Account;
import com.example.entity.ApiConfig;
import com.example.mapper.ApiConfigMapper;
import com.example.service.ApiConfigService;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 接口配置业务处理实现
 **/
@Service
public class ApiConfigServiceImpl implements ApiConfigService {

    @Resource
    private ApiConfigMapper apiConfigMapper;

    @Override
    public void add(ApiConfig apiConfig) {
        Account currentUser = TokenUtils.getCurrentUser();
        
        // 设置创建人信息
        apiConfig.setCreatorId(currentUser.getId());
        apiConfig.setCreatorName(currentUser.getName());
        
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
        
        apiConfigMapper.insert(apiConfig);
    }

    @Override
    public void deleteById(Integer id) {
        apiConfigMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            apiConfigMapper.deleteById(id);
        }
    }

    @Override
    public void updateById(ApiConfig apiConfig) {
        apiConfigMapper.updateById(apiConfig);
    }

    @Override
    public ApiConfig selectById(Integer id) {
        return apiConfigMapper.selectById(id);
    }

    @Override
    public List<ApiConfig> selectAll(ApiConfig apiConfig) {
        return apiConfigMapper.selectAll(apiConfig);
    }

    @Override
    public PageInfo<ApiConfig> selectPage(ApiConfig apiConfig, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ApiConfig> list = apiConfigMapper.selectAll(apiConfig);
        return PageInfo.of(list);
    }

    @Override
    public List<ApiConfig> selectByStatus(String status) {
        return apiConfigMapper.selectByStatus(status);
    }

    @Override
    public List<ApiConfig> selectByType(String type) {
        return apiConfigMapper.selectByType(type);
    }

    @Override
    public String testApi(Integer id) {
        return "";
    }

//    @Override
//    public String testApi(Integer id) {
//        ApiConfig apiConfig = apiConfigMapper.selectById(id);
//        if (apiConfig == null) {
//            throw new RuntimeException("接口配置不存在");
//        }
//
//        try {
//            HttpRequest request = HttpRequest.create(apiConfig.getUrl())
//                    .timeout(apiConfig.getTimeout() * 1000);
//
//            // 设置请求方法
//            switch (apiConfig.getMethod().toUpperCase()) {
//                case "GET":
//                    request = request.get();
//                    break;
//                case "POST":
//                    request = request.post();
//                    break;
//                case "PUT":
//                    request = request.put();
//                    break;
//                case "DELETE":
//                    request = request.delete();
//                    break;
//                default:
//                    request = request.get();
//            }
//
//            // 设置请求头
//            if (apiConfig.getHeaders() != null && !apiConfig.getHeaders().isEmpty()) {
//                try {
//                    Map<String, String> headers = JSONUtil.toBean(apiConfig.getHeaders(), Map.class);
//                    for (Map.Entry<String, String> entry : headers.entrySet()) {
//                        request.header(entry.getKey(), entry.getValue());
//                    }
//                } catch (Exception e) {
//                    // 忽略请求头解析错误
//                }
//            }
//
//            // 设置请求参数
//            if (apiConfig.getParameters() != null && !apiConfig.getParameters().isEmpty()) {
//                try {
//                    Map<String, Object> params = JSONUtil.toBean(apiConfig.getParameters(), Map.class);
//                    for (Map.Entry<String, Object> entry : params.entrySet()) {
//                        request.form(entry.getKey(), entry.getValue());
//                    }
//                } catch (Exception e) {
//                    // 忽略参数解析错误
//                }
//            }
//
//            // 设置认证信息
//            if (apiConfig.getAuthInfo() != null && !apiConfig.getAuthInfo().isEmpty()) {
//                try {
//                    Map<String, String> authInfo = JSONUtil.toBean(apiConfig.getAuthInfo(), Map.class);
//                    switch (apiConfig.getAuthType()) {
//                        case "Token":
//                            if (authInfo.containsKey("token")) {
//                                request.header("Authorization", "Bearer " + authInfo.get("token"));
//                            }
//                            break;
//                        case "API Key":
//                            if (authInfo.containsKey("api_key")) {
//                                request.header("X-API-Key", authInfo.get("api_key"));
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                } catch (Exception e) {
//                    // 忽略认证信息解析错误
//                }
//            }
//
//            // 执行请求
//            HttpResponse response = request.execute();
//
//            // 返回响应结果
//            return response.body();
//
//        } catch (Exception e) {
//            return "请求失败：" + e.getMessage();
//        }
//    }
} 