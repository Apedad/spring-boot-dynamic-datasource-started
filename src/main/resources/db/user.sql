/*
 Navicat Premium Data Transfer

 Source Server         : 本地库
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : db_master

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 19/01/2018 18:38:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1000, 'admin', 'admin', '2018-01-19 16:43:38', '2018-01-19 16:43:40');
INSERT INTO `user` VALUES (1001, 'rocliu', 'rocliu', '2018-01-19 16:43:58', '2018-01-19 16:44:00');

SET FOREIGN_KEY_CHECKS = 1;
