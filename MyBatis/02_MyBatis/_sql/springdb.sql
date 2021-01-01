/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50160
 Source Host           : localhost:3306
 Source Schema         : springdb

 Target Server Type    : MySQL
 Target Server Version : 50160
 File Encoding         : 65001

 Date: 01/01/2021 14:29:21
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mybatis
-- ----------------------------
DROP TABLE IF EXISTS `mybatis`;
CREATE TABLE `mybatis`  (
  `id` int(8) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mybatis
-- ----------------------------
INSERT INTO `mybatis` VALUES (1001, 'zs', 'sz', 28);
INSERT INTO `mybatis` VALUES (1002, 'ls', 'sl', 20);

SET FOREIGN_KEY_CHECKS = 1;
