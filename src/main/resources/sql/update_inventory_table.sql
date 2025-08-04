-- 更新资产盘点表结构
-- 如果表已存在，先删除
DROP TABLE IF EXISTS `inventory`;

-- 重新创建资产盘点表
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `assets_id` int(11) NOT NULL COMMENT '资产ID',
  `inventory_time` varchar(20) NOT NULL COMMENT '盘点时间',
  `staff_id` int(11) NOT NULL COMMENT '盘点人ID',
  `staff_name` varchar(50) NOT NULL COMMENT '盘点人姓名',
  `result` varchar(20) NOT NULL DEFAULT '正常' COMMENT '盘点结果(正常、异常、丢失)',
  `status` varchar(20) NOT NULL DEFAULT '待盘点' COMMENT '盘点状态(待盘点、已盘点、已确认)',
  `location` varchar(100) DEFAULT NULL COMMENT '盘点位置',
  `comment` text COMMENT '备注',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_assets_id` (`assets_id`),
  KEY `idx_staff_id` (`staff_id`),
  KEY `idx_inventory_time` (`inventory_time`),
  KEY `idx_result` (`result`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资产盘点表';

-- 插入一些示例数据（确保assets_id对应的资产存在）
INSERT INTO `inventory` (`assets_id`, `inventory_time`, `staff_id`, `staff_name`, `result`, `status`, `location`, `comment`, `create_time`, `update_time`) VALUES
(1, '2024-01-15 10:00:00', 1, '张三', '正常', '已盘点', '办公室A区', '设备运行正常', '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
(2, '2024-01-15 11:00:00', 1, '张三', '正常', '已盘点', '办公室B区', '设备运行正常', '2024-01-15 11:00:00', '2024-01-15 11:00:00'),
(3, '2024-01-16 09:00:00', 2, '李四', '异常', '已盘点', '会议室', '设备需要维修', '2024-01-16 09:00:00', '2024-01-16 09:00:00'),
(4, '2024-01-16 14:00:00', 2, '李四', '正常', '已盘点', '办公室C区', '设备运行正常', '2024-01-16 14:00:00', '2024-01-16 14:00:00'),
(5, '2024-01-17 10:00:00', 1, '张三', '正常', '已盘点', '办公室D区', '设备运行正常', '2024-01-17 10:00:00', '2024-01-17 10:00:00'); 