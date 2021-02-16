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

 Date: 16/02/2021 18:48:53
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
INSERT INTO `mybatis` VALUES (1003, 'foo', 'foo.@qq.com', 21);
INSERT INTO `mybatis` VALUES (1004, 'foo', 'foo@qq.com', 21);
INSERT INTO `mybatis` VALUES (1005, 'bar', 'bar@qq.com', 23);
INSERT INTO `mybatis` VALUES (1006, 'd d', '1685294601@qq.com', 21);
INSERT INTO `mybatis` VALUES (1010, 'd d', '1685294601@qq.com', 21);
INSERT INTO `mybatis` VALUES (1011, 'd d', '1685294601@qq.com', 21);
INSERT INTO `mybatis` VALUES (1012, 'foo', '1685294601@qq.com', 21);
INSERT INTO `mybatis` VALUES (1013, 'foo', '1685294601@qq.com', 21);
INSERT INTO `mybatis` VALUES (1018, 'd d', '1685294601@qq.com', 21);
INSERT INTO `mybatis` VALUES (1019, 'd d', '1685294601@qq.com', 21);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'foo', 21);
INSERT INTO `student` VALUES (2, 'bar、', 21);
INSERT INTO `student` VALUES (3, 'bar', 21);
INSERT INTO `student` VALUES (4, 'fooo', 21);
INSERT INTO `student` VALUES (5, 'barr', 21);

SET FOREIGN_KEY_CHECKS = 1;
