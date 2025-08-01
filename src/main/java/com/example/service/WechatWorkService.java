package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.entity.WechatWorkConfig;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信集成服务
 */
@Service
public class WechatWorkService {

    private static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    private static final String USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
    private static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

    /**
     * 获取访问令牌
     */
    public String getAccessToken(WechatWorkConfig config) {
        Map<String, Object> params = new HashMap<>();
        params.put("corpid", config.getCorpId());
        params.put("corpsecret", config.getAgentSecret());
        
        String result = HttpUtil.get(ACCESS_TOKEN_URL, params);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        
        if (jsonObject.getInt("errcode", -1) == 0) {
            return jsonObject.getStr("access_token");
        } else {
            throw new CustomException("500", "获取企业微信访问令牌失败：" + jsonObject.getStr("errmsg"));
        }
    }

    /**
     * 获取用户信息
     */
    public JSONObject getUserInfo(String accessToken, String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("code", code);
        
        String result = HttpUtil.get(USER_INFO_URL, params);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        
        if (jsonObject.getInt("errcode", -1) == 0) {
            return jsonObject;
        } else {
            throw new CustomException("500", "获取用户信息失败：" + jsonObject.getStr("errmsg"));
        }
    }

    /**
     * 发送消息
     */
    public boolean sendMessage(WechatWorkConfig config, String userId, String content) {
        String accessToken = getAccessToken(config);
        
        JSONObject message = new JSONObject();
        message.set("touser", userId);
        message.set("msgtype", "text");
        message.set("agentid", config.getAgentId());
        
        JSONObject text = new JSONObject();
        text.set("content", content);
        message.set("text", text);
        
        String result = HttpUtil.post(SEND_MESSAGE_URL + "?access_token=" + accessToken, message.toString());
        JSONObject jsonObject = JSONUtil.parseObj(result);
        
        return jsonObject.getInt("errcode", -1) == 0;
    }

    /**
     * 验证配置
     */
    public boolean validateConfig(WechatWorkConfig config) {
        try {
            getAccessToken(config);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 