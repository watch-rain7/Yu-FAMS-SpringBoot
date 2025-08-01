package com.example.controller;

import com.example.common.Result;
import com.example.entity.DingTalkConfig;
import com.example.entity.WechatWorkConfig;
import com.example.service.WechatWorkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 集成管理控制器
 */
@RestController
@RequestMapping("/integration")
public class IntegrationController {

    @Resource
    private WechatWorkService wechatWorkService;

    /**
     * 企业微信登录
     */
    @PostMapping("/wechat/login")
    public Result wechatLogin(@RequestParam String code) {
        try {
            // 这里应该从数据库获取配置，暂时使用硬编码
            WechatWorkConfig config = new WechatWorkConfig();
            config.setCorpId("your_corp_id");
            config.setAgentSecret("your_agent_secret");
            
            String accessToken = wechatWorkService.getAccessToken(config);
            cn.hutool.json.JSONObject userInfo = wechatWorkService.getUserInfo(accessToken, code);
            
            return Result.success(userInfo);
        } catch (Exception e) {
            return Result.error("500", "企业微信登录失败：" + e.getMessage());
        }
    }

    /**
     * 发送企业微信消息
     */
    @PostMapping("/wechat/sendMessage")
    public Result sendWechatMessage(@RequestParam String userId, @RequestParam String content) {
        try {
            WechatWorkConfig config = new WechatWorkConfig();
            config.setCorpId("your_corp_id");
            config.setAgentSecret("your_agent_secret");
            config.setAgentId("your_agent_id");
            
            boolean success = wechatWorkService.sendMessage(config, userId, content);
            if (success) {
                return Result.success("消息发送成功");
            } else {
                return Result.error("500", "消息发送失败");
            }
        } catch (Exception e) {
            return Result.error("500", "发送消息失败：" + e.getMessage());
        }
    }

    /**
     * 验证企业微信配置
     */
    @PostMapping("/wechat/validate")
    public Result validateWechatConfig(@RequestBody WechatWorkConfig config) {
        try {
            boolean valid = wechatWorkService.validateConfig(config);
            if (valid) {
                return Result.success("配置验证成功");
            } else {
                return Result.error("500", "配置验证失败");
            }
        } catch (Exception e) {
            return Result.error("500", "配置验证失败：" + e.getMessage());
        }
    }

    /**
     * 获取企业微信授权URL
     */
    @GetMapping("/wechat/authUrl")
    public Result getWechatAuthUrl(@RequestParam String redirectUri) {
        try {
            WechatWorkConfig config = new WechatWorkConfig();
            config.setCorpId("your_corp_id");
            
            String authUrl = String.format(
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect",
                config.getCorpId(),
                redirectUri
            );
            
            return Result.success(authUrl);
        } catch (Exception e) {
            return Result.error("500", "获取授权URL失败：" + e.getMessage());
        }
    }
} 