/*
 Navicat Premium Data Transfer

 Source Server         : root@localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mybatis_plus

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 29/10/2021 11:29:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `version` int(10) NULL DEFAULT 1 COMMENT '乐观锁',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1453604789488209923 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', 1, 0, NULL, NULL);
INSERT INTO `user` VALUES (2, '啊旺', 20, 'test2@baomidou.com', 1, 0, NULL, NULL);
INSERT INTO `user` VALUES (3, '帅小伙222', 18, '321jdw@qq.com', 2, 0, NULL, '2021-10-28 11:34:54');
INSERT INTO `user` VALUES (4, '小蒋', 21, 'test4@baomidou.com', 1, 0, NULL, NULL);
INSERT INTO `user` VALUES (5, '定位', 24, 'test5@baomidou.com', 1, 0, NULL, NULL);
INSERT INTO `user` VALUES (1453564943537397762, '二三三', 29, '2455555659@qq.com', 1, 0, '2021-10-28 11:31:03', '2021-10-28 11:31:03');
INSERT INTO `user` VALUES (1453566091560271874, '99三', 29, '2455555659@qq.com', 1, 0, '2021-10-28 11:35:37', '2021-10-28 11:35:37');
INSERT INTO `user` VALUES (1453604789488209922, '不想996', 699, '6969696@qq.com', 1, 0, '2021-10-28 14:09:23', '2021-10-28 14:09:23');

SET FOREIGN_KEY_CHECKS = 1;
