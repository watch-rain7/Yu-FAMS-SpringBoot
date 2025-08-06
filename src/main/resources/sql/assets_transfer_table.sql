-- 资产调拨表
CREATE TABLE IF NOT EXISTS `assets_transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `assets_id` int(11) NOT NULL COMMENT '资产ID',
  `assets_name` varchar(100) NOT NULL COMMENT '资产名称',
  `assets_no` varchar(50) NOT NULL COMMENT '资产编号',
  `transfer_time` varchar(20) NOT NULL COMMENT '调拨时间',
  `transfer_staff_id` int(11) NOT NULL COMMENT '调拨人ID',
  `transfer_staff_name` varchar(50) NOT NULL COMMENT '调拨人姓名',
  `from_staff_id` int(11) DEFAULT NULL COMMENT '原使用人ID',
  `from_staff_name` varchar(50) DEFAULT NULL COMMENT '原使用人姓名',
  `from_department_id` int(11) DEFAULT NULL COMMENT '原部门ID',
  `from_department_name` varchar(50) DEFAULT NULL COMMENT '原部门名称',
  `to_staff_id` int(11) NOT NULL COMMENT '新使用人ID',
  `to_staff_name` varchar(50) NOT NULL COMMENT '新使用人姓名',
  `to_department_id` int(11) NOT NULL COMMENT '新部门ID',
  `to_department_name` varchar(50) NOT NULL COMMENT '新部门名称',
  `reason` varchar(200) DEFAULT NULL COMMENT '调拨原因',
  `status` varchar(20) NOT NULL DEFAULT '待审批' COMMENT '调拨状态(待审批、已审批、已调拨、已拒绝)',
  `approver_id` int(11) DEFAULT NULL COMMENT '审批人ID',
  `approver_name` varchar(50) DEFAULT NULL COMMENT '审批人姓名',
  `approve_time` varchar(20) DEFAULT NULL COMMENT '审批时间',
  `approve_comment` varchar(200) DEFAULT NULL COMMENT '审批意见',
  `comment` text COMMENT '备注',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_assets_id` (`assets_id`),
  KEY `idx_transfer_staff_id` (`transfer_staff_id`),
  KEY `idx_from_staff_id` (`from_staff_id`),
  KEY `idx_to_staff_id` (`to_staff_id`),
  KEY `idx_status` (`status`),
  KEY `idx_transfer_time` (`transfer_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资产调拨表';

-- 插入示例数据
INSERT INTO `assets_transfer` (`assets_id`, `assets_name`, `assets_no`, `transfer_time`, `transfer_staff_id`, `transfer_staff_name`, 
`from_staff_id`, `from_staff_name`, `from_department_id`, `from_department_name`, `to_staff_id`, `to_staff_name`, 
`to_department_id`, `to_department_name`, `reason`, `status`, `comment`, `create_time`, `update_time`) VALUES
(1, '办公电脑', 'ZC001', '2024-01-15 10:00:00', 1, '张三', 1, '张三', 1, '技术部', 2, '李四', 2, '销售部', '工作需要调拨', '已调拨', '正常调拨', '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
(2, '打印机', 'ZC002', '2024-01-16 14:00:00', 1, '张三', 2, '李四', 2, '销售部', 3, '王五', 3, '财务部', '部门调整', '已审批', '等待调拨', '2024-01-16 14:00:00', '2024-01-16 14:00:00'),
(3, '投影仪', 'ZC003', '2024-01-17 09:00:00', 2, '李四', 3, '王五', 3, '财务部', 1, '张三', 1, '技术部', '会议需要', '待审批', '等待审批', '2024-01-17 09:00:00', '2024-01-17 09:00:00'); 