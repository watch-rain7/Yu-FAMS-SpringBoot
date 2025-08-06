-- 资产详情记录表
CREATE TABLE IF NOT EXISTS `assets_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `assets_id` int(11) NOT NULL COMMENT '资产ID',
  `assets_name` varchar(100) NOT NULL COMMENT '资产名称',
  `assets_no` varchar(50) NOT NULL COMMENT '资产编号',
  `operation_type` varchar(20) NOT NULL COMMENT '操作类型(入库、领用、调拨、维修、盘点、报废等)',
  `operation_time` varchar(20) NOT NULL COMMENT '操作时间',
  `operator_id` int(11) NOT NULL COMMENT '操作人ID',
  `operator_name` varchar(50) NOT NULL COMMENT '操作人姓名',
  `before_status` varchar(20) DEFAULT NULL COMMENT '操作前状态',
  `after_status` varchar(20) DEFAULT NULL COMMENT '操作后状态',
  `before_staff_id` int(11) DEFAULT NULL COMMENT '操作前使用人ID',
  `before_staff_name` varchar(50) DEFAULT NULL COMMENT '操作前使用人姓名',
  `after_staff_id` int(11) DEFAULT NULL COMMENT '操作后使用人ID',
  `after_staff_name` varchar(50) DEFAULT NULL COMMENT '操作后使用人姓名',
  `before_department_id` int(11) DEFAULT NULL COMMENT '操作前部门ID',
  `before_department_name` varchar(50) DEFAULT NULL COMMENT '操作前部门名称',
  `after_department_id` int(11) DEFAULT NULL COMMENT '操作后部门ID',
  `after_department_name` varchar(50) DEFAULT NULL COMMENT '操作后部门名称',
  `before_location` varchar(100) DEFAULT NULL COMMENT '操作前位置',
  `after_location` varchar(100) DEFAULT NULL COMMENT '操作后位置',
  `description` varchar(500) DEFAULT NULL COMMENT '操作描述',
  `comment` text COMMENT '备注',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_assets_id` (`assets_id`),
  KEY `idx_operation_type` (`operation_type`),
  KEY `idx_operation_time` (`operation_time`),
  KEY `idx_operator_id` (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资产详情记录表';

-- 插入示例数据
INSERT INTO `assets_detail` (`assets_id`, `assets_name`, `assets_no`, `operation_type`, `operation_time`, `operator_id`, `operator_name`, 
`before_status`, `after_status`, `before_staff_id`, `before_staff_name`, `after_staff_id`, `after_staff_name`,
`before_department_id`, `before_department_name`, `after_department_id`, `after_department_name`,
`before_location`, `after_location`, `description`, `comment`, `create_time`) VALUES
(1, '办公电脑', 'ZC001', '入库', '2024-01-01 09:00:00', 1, '张三', NULL, '库存', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '仓库A区', '资产入库', '新购入资产', '2024-01-01 09:00:00'),
(1, '办公电脑', 'ZC001', '领用', '2024-01-02 10:00:00', 1, '张三', '库存', '使用中', NULL, NULL, 1, '张三', NULL, NULL, 1, '技术部', '仓库A区', '办公室A区', '资产领用', '正常领用', '2024-01-02 10:00:00'),
(1, '办公电脑', 'ZC001', '调拨', '2024-01-15 10:00:00', 1, '张三', '使用中', '使用中', 1, '张三', 2, '李四', 1, '技术部', 2, '销售部', '办公室A区', '办公室B区', '资产调拨', '工作需要调拨', '2024-01-15 10:00:00'),
(2, '打印机', 'ZC002', '入库', '2024-01-03 14:00:00', 1, '张三', NULL, '库存', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '仓库B区', '资产入库', '新购入资产', '2024-01-03 14:00:00'),
(2, '打印机', 'ZC002', '领用', '2024-01-04 15:00:00', 2, '李四', '库存', '使用中', NULL, NULL, 2, '李四', NULL, NULL, 2, '销售部', '仓库B区', '办公室B区', '资产领用', '正常领用', '2024-01-04 15:00:00'),
(3, '投影仪', 'ZC003', '入库', '2024-01-05 11:00:00', 1, '张三', NULL, '库存', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '仓库C区', '资产入库', '新购入资产', '2024-01-05 11:00:00'),
(3, '投影仪', 'ZC003', '领用', '2024-01-06 16:00:00', 3, '王五', '库存', '使用中', NULL, NULL, 3, '王五', NULL, NULL, 3, '财务部', '仓库C区', '会议室', '资产领用', '正常领用', '2024-01-06 16:00:00'); 