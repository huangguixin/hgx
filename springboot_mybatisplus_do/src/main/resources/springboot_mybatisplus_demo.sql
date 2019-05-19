/*
Navicat MySQL Data Transfer

Source Server         : books
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : springboot_mybatisplus_demo

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2019-02-24 18:32:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hgx_user
-- ----------------------------
DROP TABLE IF EXISTS `hgx_user`;
CREATE TABLE `hgx_user` (
  `id` bigint(255) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `gender` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `logic_flag` int(11) DEFAULT '1',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
