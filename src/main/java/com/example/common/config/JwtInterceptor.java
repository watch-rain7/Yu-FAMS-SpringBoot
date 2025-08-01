package com.example.common.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦截器 - 优化版本
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private AdminService adminService;

    @Resource
    private StaffService staffService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从header或参数中获取token
        String token = request.getHeader(Constants.TOKEN);
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(Constants.TOKEN);
        }

        // 2. 验证token是否存在
        if (StrUtil.isBlank(token)) {
            log.warn("请求路径 {} 缺少Token", request.getRequestURI());
            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }

        // 3. 解析token并获取用户信息
        Account account = null;
        String userId = null;
        String role = null;

        try {
            // 3.1 解码token
            String userRole = JWT.decode(token).getAudience().get(0);

            // 3.2 安全分割字符串
            if (!userRole.contains("-")) {
                throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR.getCode(), "Token格式错误");
            }

            String[] parts = userRole.split("-", 2);
            userId = parts[0];
            role = parts[1];

            // 3.3 根据角色查询用户
            if (RoleEnum.ADMIN.name().equals(role)) {
                account = adminService.selectById(Integer.valueOf(userId));
            } else if (RoleEnum.STAFF.name().equals(role)) {
                account = staffService.selectById(Integer.valueOf(userId));
            } else {
                throw new CustomException(ResultCodeEnum.NO_PERMISSION);
            }
        } catch (Exception e) {
            // 添加详细的错误日志
            log.error("Token解析失败: {} | Token: {}", e.getMessage(), token, e);
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR.getCode(), "Token解析失败: " + e.getMessage());
        }

        // 4. 验证用户是否存在
        if (ObjectUtil.isNull(account)) {
            log.warn("用户不存在: ID={}, Role={}", userId, role);
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 5. 验证token有效性
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
            jwtVerifier.verify(token);
        } catch (TokenExpiredException e) {
            log.warn("Token已过期: {}", token);
            throw new CustomException(ResultCodeEnum.TOKEN_EXPIRED_ERROR);
        } catch (JWTVerificationException e) {
            log.error("Token验证失败: {} | Account: {}", e.getMessage(), account.getUsername(), e);
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR.getCode(), "Token验证失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("Token验证异常: {}", e.getMessage(), e);
            throw new CustomException(ResultCodeEnum.SYSTEM_ERROR.getCode(), "Token验证异常");
        }

        return true;
    }
}