-- 接口配置表
CREATE TABLE IF NOT EXISTS `api_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '接口名称',
  `description` varchar(500) DEFAULT NULL COMMENT '接口描述',
  `url` varchar(500) NOT NULL COMMENT '接口URL',
  `method` varchar(10) NOT NULL DEFAULT 'GET' COMMENT '请求方法(GET、POST、PUT、DELETE)',
  `headers` text COMMENT '请求头(JSON格式)',
  `parameters` text COMMENT '请求参数(JSON格式)',
  `response_format` varchar(20) DEFAULT 'JSON' COMMENT '响应格式(JSON、XML、TEXT)',
  `status` varchar(20) NOT NULL DEFAULT '启用' COMMENT '接口状态(启用、禁用)',
  `type` varchar(20) NOT NULL DEFAULT '内部接口' COMMENT '接口类型(内部接口、外部接口)',
  `auth_type` varchar(20) DEFAULT '无认证' COMMENT '认证方式(无认证、Token、OAuth2、API Key)',
  `auth_info` text COMMENT '认证信息(JSON格式)',
  `timeout` int(11) DEFAULT 30 COMMENT '超时时间(秒)',
  `retry_count` int(11) DEFAULT 0 COMMENT '重试次数',
  `creator_id` int(11) NOT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_status` (`status`),
  KEY `idx_type` (`type`),
  KEY `idx_creator_id` (`creator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='接口配置表';

-- 插入示例数据
INSERT INTO `api_config` (`name`, `description`, `url`, `method`, `headers`, `parameters`, `response_format`, `status`, `type`, `auth_type`, `auth_info`, `timeout`, `retry_count`, `creator_id`, `creator_name`, `create_time`, `update_time`) VALUES
('获取资产列表', '获取所有资产信息', '/api/assets/list', 'GET', '{"Content-Type": "application/json"}', '{"page": 1, "size": 10}', 'JSON', '启用', '内部接口', 'Token', '{"token": "xxx"}', 30, 3, 1, '张三', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
('创建资产', '创建新的资产记录', '/api/assets/create', 'POST', '{"Content-Type": "application/json"}', '{"name": "资产名称", "category": "分类"}', 'JSON', '启用', '内部接口', 'Token', '{"token": "xxx"}', 30, 3, 1, '张三', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
('更新资产', '更新资产信息', '/api/assets/update', 'PUT', '{"Content-Type": "application/json"}', '{"id": 1, "name": "新名称"}', 'JSON', '启用', '内部接口', 'Token', '{"token": "xxx"}', 30, 3, 1, '张三', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
('删除资产', '删除指定资产', '/api/assets/delete', 'DELETE', '{"Content-Type": "application/json"}', '{"id": 1}', 'JSON', '启用', '内部接口', 'Token', '{"token": "xxx"}', 30, 3, 1, '张三', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
('外部天气API', '获取天气信息', 'https://api.weather.com/current', 'GET', '{"Accept": "application/json"}', '{"city": "北京"}', 'JSON', '启用', '外部接口', 'API Key', '{"api_key": "xxx"}', 60, 2, 1, '张三', '2024-01-01 10:00:00', '2024-01-01 10:00:00'); 