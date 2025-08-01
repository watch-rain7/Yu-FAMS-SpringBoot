-- 企业微信配置表
CREATE TABLE IF NOT EXISTS `wechat_work_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `corp_id` varchar(100) NOT NULL COMMENT '企业ID',
  `agent_secret` varchar(100) NOT NULL COMMENT '应用Secret',
  `agent_id` varchar(50) NOT NULL COMMENT '应用ID',
  `token` varchar(100) DEFAULT NULL COMMENT '回调Token',
  `encoding_aes_key` varchar(100) DEFAULT NULL COMMENT '回调EncodingAESKey',
  `enabled` tinyint(1) DEFAULT 0 COMMENT '是否启用',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业微信配置表';

-- 钉钉配置表
CREATE TABLE IF NOT EXISTS `dingtalk_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `app_key` varchar(100) NOT NULL COMMENT '应用Key',
  `app_secret` varchar(100) NOT NULL COMMENT '应用Secret',
  `corp_id` varchar(100) NOT NULL COMMENT '企业ID',
  `token` varchar(100) DEFAULT NULL COMMENT '回调Token',
  `aes_key` varchar(100) DEFAULT NULL COMMENT '回调AESKey',
  `enabled` tinyint(1) DEFAULT 0 COMMENT '是否启用',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='钉钉配置表';

-- 第三方登录用户关联表
CREATE TABLE IF NOT EXISTS `third_party_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '本地用户ID',
  `third_party_type` varchar(20) NOT NULL COMMENT '第三方类型(wechat/dingtalk)',
  `third_party_user_id` varchar(100) NOT NULL COMMENT '第三方用户ID',
  `third_party_user_info` text COMMENT '第三方用户信息JSON',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_third` (`user_id`, `third_party_type`),
  UNIQUE KEY `uk_third_user` (`third_party_type`, `third_party_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='第三方登录用户关联表';

-- 插入默认配置数据
INSERT INTO `wechat_work_config` (`corp_id`, `agent_secret`, `agent_id`, `enabled`, `create_time`) 
VALUES ('your_corp_id', 'your_agent_secret', 'your_agent_id', 0, NOW())
ON DUPLICATE KEY UPDATE `update_time` = NOW();

INSERT INTO `dingtalk_config` (`app_key`, `app_secret`, `corp_id`, `enabled`, `create_time`) 
VALUES ('your_app_key', 'your_app_secret', 'your_corp_id', 0, NOW())
ON DUPLICATE KEY UPDATE `update_time` = NOW(); 