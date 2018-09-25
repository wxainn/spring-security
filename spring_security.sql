/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : spring_security

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 09/25/2018 18:54:14 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：资源id',
  `parentId` int(11) DEFAULT NULL COMMENT '父资源id',
  `name` varchar(30) NOT NULL COMMENT '资源名',
  `description` varchar(100) DEFAULT NULL COMMENT '资源描述',
  `value` varchar(200) NOT NULL COMMENT '资源值',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '资源类型 (0：菜单；10：操作；)',
  `priority` smallint(6) NOT NULL DEFAULT '0' COMMENT '优先级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='资源（菜单或操作）';

-- ----------------------------
--  Records of `resource`
-- ----------------------------
BEGIN;
INSERT INTO `resource` VALUES ('1', '0', '系统管理', '系统管理', '#', '0', '10'), ('2', '1', '资源管理', '资源管理', '/resource', '0', '10'), ('3', '1', '角色管理', '角色管理', '/role', '0', '20'), ('4', '1', '账号管理', '账号管理', '/user', '0', '30'), ('5', '1', '管理员', '管理员', '/admin', '0', '40'), ('6', '5', '管理员信息', '查看当前登录管理员信息', '/admin/current', '0', '10'), ('7', '4', '登陆用户', '登陆用户', '/user/current', '0', '10');
COMMIT;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：角色id',
  `name` varchar(30) NOT NULL COMMENT '角色名称',
  `displayName` varchar(30) DEFAULT NULL COMMENT '角色显示名称',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `deletable` bit(1) DEFAULT b'1' COMMENT '是否可删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('1', 'SUPER_ADMIN', '超级管理员', '系统超级管理员，拥有最高权限	', b'0'), ('2', 'ADMIN', '管理员', '管理员', b'1'), ('3', 'USER', '用户', '普通用户	', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `resourceId` int(11) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色资源';

-- ----------------------------
--  Records of `role_resource`
-- ----------------------------
BEGIN;
INSERT INTO `role_resource` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('4', '1', '4'), ('5', '2', '3'), ('6', '1', '6'), ('7', '1', '7'), ('8', '2', '7'), ('9', '2', '7'), ('10', '3', '7'), ('11', '2', '6');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 用户id',
  `username` varchar(30) NOT NULL COMMENT '用户帐号',
  `password` varchar(100) NOT NULL COMMENT '密码（加密）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `security_account_username_uindex` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'superAdmin', '{bcrypt}$2a$10$CL0cbNxzXQXJb75wbsc7pu75IBKhwO6isDS7BZp.RUIqs0JwjSHxS'), ('2', 'admin', '{bcrypt}$2a$10$CL0cbNxzXQXJb75wbsc7pu75IBKhwO6isDS7BZp.RUIqs0JwjSHxS'), ('3', 'user', '{bcrypt}$2a$10$CL0cbNxzXQXJb75wbsc7pu75IBKhwO6isDS7BZp.RUIqs0JwjSHxS');
COMMIT;

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '帐号id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
--  Records of `user_role`
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES ('1', '1', '1'), ('2', '2', '2'), ('3', '3', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
