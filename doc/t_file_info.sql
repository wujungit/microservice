/*
Navicat MySQL Data Transfer

Source Server         : sit
Source Server Version : 50641
Source Host           : 139.159.144.194:3306
Source Database       : db_ncdp_common_service

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2019-05-20 14:46:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_file_info
-- ----------------------------
DROP TABLE IF EXISTS `t_file_info`;
CREATE TABLE `t_file_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名称',
  `file_real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件真实名称',
  `file_type` tinyint(1) DEFAULT NULL COMMENT '文件类型（0-文件，1-图像，2-临时文件）',
  `file_suffix` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '文件后缀',
  `file_md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '文件md5值',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小，单位：KB',
  `file_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件URL',
  `relative_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件相对路径',
  `absolute_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件绝对路径',
  `file_origin` tinyint(1) DEFAULT NULL COMMENT '文件来源（0-管理端，1-医生端，2-会员端，3-小程序）',
  `model_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '模型ID（用于业务标识）',
  `operator` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '删除标记（0-正常，1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=566 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件信息表';
