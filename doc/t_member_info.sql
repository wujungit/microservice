/*
Navicat MySQL Data Transfer

Source Server         : sit
Source Server Version : 50641
Source Host           : 139.159.144.194:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2019-04-11 10:42:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_member_info
-- ----------------------------
DROP TABLE IF EXISTS `t_member_info`;
CREATE TABLE `t_member_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_code` varchar(32) DEFAULT NULL COMMENT '会员编码',
  `portrait` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别（0-男，1-女）',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `height` decimal(8,2) DEFAULT NULL COMMENT '身高（cm）',
  `weight` decimal(8,2) DEFAULT NULL COMMENT '体重（kg）',
  `id_number` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `pay_password` varchar(32) DEFAULT NULL COMMENT '支付密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '加密的盐',
  `origin` tinyint(1) DEFAULT NULL COMMENT '来源（0-管理端，1-会员端，2-医生端）',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（0-正常，1禁用）',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `province` varchar(16) DEFAULT NULL COMMENT '省',
  `city` varchar(16) DEFAULT NULL COMMENT '市',
  `district` varchar(16) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `completed` bit(1) DEFAULT NULL COMMENT '是否已完善个人资料（0-否，1-是）',
  `if_bind_wechat` bit(1) DEFAULT NULL COMMENT '是否已绑定微信（0-否，1-是）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识（0-正常，1-删除）',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员信息表';
