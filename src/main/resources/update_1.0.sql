-- 2020-07-21 19:47:25 by MyName
CREATE TABLE `bis_task`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '任务表';
-- 2020-07-21 20:02:11 by MyName
ALTER TABLE `bis_task` ADD COLUMN `task_name` varchar(100) NOT NULL COMMENT '任务名称' AFTER `id`;
-- 2020-07-21 20:02:11 by MyName
ALTER TABLE `bis_task` ADD COLUMN `task_desc` varchar(100) COMMENT '任务描述' AFTER `task_name`;
-- 2020-07-21 20:02:11 by MyName
ALTER TABLE `bis_task` ADD COLUMN `task_type` int(8) DEFAULT 0 COMMENT '任务类型 ' AFTER `task_desc`;
-- 2020-07-21 20:02:11 by MyName
ALTER TABLE `bis_task` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `task_type`;
-- 2020-07-21 20:06:40 by MyName
CREATE TABLE `bis_task_step`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '任务步骤表';
-- 2020-07-21 20:09:52 by MyName
ALTER TABLE `bis_task_step` ADD COLUMN `bis_task_id` bigint NOT NULL COMMENT '任务表' AFTER `id`;
-- 2020-07-21 20:09:53 by MyName
ALTER TABLE `bis_task_step` ADD COLUMN `task_step_name` varchar(100) NOT NULL COMMENT '任务步骤名称' AFTER `bis_task_id`;
-- 2020-07-21 20:09:53 by MyName
ALTER TABLE `bis_task_step` ADD COLUMN `task_step_desc` varchar(100) COMMENT '任务步骤描述' AFTER `task_step_name`;
-- 2020-07-21 20:09:53 by MyName
ALTER TABLE `bis_task_step` ADD COLUMN `task_step_start_time` timestamp NULL COMMENT '任务步骤开始时间' AFTER `task_step_desc`;
-- 2020-07-21 20:09:54 by MyName
ALTER TABLE `bis_task_step` ADD COLUMN `task_step_end_time` timestamp NULL COMMENT '任务步骤结束时间' AFTER `task_step_start_time`;
-- 2020-07-21 20:09:54 by MyName
ALTER TABLE `bis_task_step` ADD COLUMN `task_step_price` bigint COMMENT '任务步骤单价' AFTER `task_step_end_time`;
-- 2020-07-21 20:09:54 by MyName
ALTER TABLE `bis_task_step` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `task_step_price`;
