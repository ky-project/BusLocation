/*
 Navicat Premium Data Transfer

 Source Server         : try1
 Source Server Type    : MySQL
 Source Server Version : 50096
 Source Host           : localhost:3306
 Source Schema         : tt1

 Target Server Type    : MySQL
 Target Server Version : 50096
 File Encoding         : 65001

 Date: 11/04/2019 23:40:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_department
-- ----------------------------
DROP TABLE IF EXISTS `b_department`;
CREATE TABLE `b_department`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CODE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加索引unique_',
  `UPLEVEL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENTID` int(11) NULL DEFAULT NULL,
  `LEAF` tinyint(1) NULL DEFAULT NULL COMMENT '1-叶子  0-树枝',
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  UNIQUE INDEX `index_code` USING BTREE(`CODE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for file_attach
-- ----------------------------
DROP TABLE IF EXISTS `file_attach`;
CREATE TABLE `file_attach`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `FILENAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FILEPATH` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EXT` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FILETYPE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NOTE` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for p_notice
-- ----------------------------
DROP TABLE IF EXISTS `p_notice`;
CREATE TABLE `p_notice`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SYS_ID` int(11) NOT NULL,
  `PN_TOPIC` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PN_CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `PN_TYPE` int(11) NULL DEFAULT NULL COMMENT '\r\n0-新闻 1-公告 2-供需发布  3-活动发布 4-法律服务 5-政策法规 6-资料下载',
  `PN_STATE` int(11) NULL DEFAULT 0 COMMENT '0-草稿\r\n            1-待审核\r\n            2-已发布',
  `PN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_12` USING BTREE(`SYS_ID`),
  CONSTRAINT `FK_Relationship_12` FOREIGN KEY (`SYS_ID`) REFERENCES `sys_user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '0-新闻\r\n1-公告\r\n\r\n4-法律服务\r\n                             -&#&; InnoDB free: 10240 kB; ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for p_notice_attach
-- ----------------------------
DROP TABLE IF EXISTS `p_notice_attach`;
CREATE TABLE `p_notice_attach`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `P_N_ID` int(11) NOT NULL,
  `FIL_ID` int(11) NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_13` USING BTREE(`P_N_ID`),
  INDEX `FK_Relationship_14` USING BTREE(`FIL_ID`),
  CONSTRAINT `FK_Relationship_14` FOREIGN KEY (`FIL_ID`) REFERENCES `file_attach` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_13` FOREIGN KEY (`P_N_ID`) REFERENCES `p_notice` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`P_N_ID`) REFER `tt1/p_notice`(`ID`); (`FIL_ID`) REFER `' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_bus
-- ----------------------------
DROP TABLE IF EXISTS `sb_bus`;
CREATE TABLE `sb_bus`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SBB_SEAT_NUM` int(11) NULL DEFAULT NULL,
  `SBB_BUS_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBB_PLATE_NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBB_DRIVER_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBB_DRIVER_TEL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_bus_route
-- ----------------------------
DROP TABLE IF EXISTS `sb_bus_route`;
CREATE TABLE `sb_bus_route`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int(11) NOT NULL,
  `SB__ID2` int(11) NOT NULL,
  `SBBR_WEEK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBBR_START_TIME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBBR_END_TIME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_6` USING BTREE(`SB__ID`),
  INDEX `FK_Relationship_7` USING BTREE(`SB__ID2`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`SB__ID2`) REFERENCES `sb_route` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`SB__ID`) REFERENCES `sb_bus` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`SB__ID`) REFER `tt1/sb_bus`(`ID`); (`SB__ID2`) REFER `t' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_busposition
-- ----------------------------
DROP TABLE IF EXISTS `sb_busposition`;
CREATE TABLE `sb_busposition`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int(11) NOT NULL,
  `SBP_RECODE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SBP_LONGITUDE` double NULL DEFAULT NULL,
  `SBP_LATITUDE` double NULL DEFAULT NULL,
  `SBP_VELOCITY` double NULL DEFAULT NULL,
  `SBP_DIRECTION` double NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_10` USING BTREE(`SB__ID`),
  CONSTRAINT `FK_Relationship_10` FOREIGN KEY (`SB__ID`) REFERENCES `sb_bus` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`SB__ID`) REFER `tt1/sb_bus`(`ID`)' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_busposition_his
-- ----------------------------
DROP TABLE IF EXISTS `sb_busposition_his`;
CREATE TABLE `sb_busposition_his`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int(11) NOT NULL,
  `SBP_RECODE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SBP_LONGITUDE` double NULL DEFAULT NULL,
  `SBP_LATITUDE` double NULL DEFAULT NULL,
  `SBP_VELOCITY` double NULL DEFAULT NULL,
  `SBP_DIRECTION` double NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_11` USING BTREE(`SB__ID`),
  CONSTRAINT `FK_Relationship_11` FOREIGN KEY (`SB__ID`) REFERENCES `sb_bus` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`SB__ID`) REFER `tt1/sb_bus`(`ID`)' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sb_role_authority`;
CREATE TABLE `sb_role_authority`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SYS_ID` int(11) NOT NULL,
  `SYS_ID2` int(11) NOT NULL,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_21` USING BTREE(`SYS_ID`),
  INDEX `FK_Relationship_22` USING BTREE(`SYS_ID2`),
  CONSTRAINT `FK_Relationship_22` FOREIGN KEY (`SYS_ID2`) REFERENCES `sys_authority` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_21` FOREIGN KEY (`SYS_ID`) REFERENCES `sys_role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`SYS_ID`) REFER `tt1/sys_role`(`ID`); (`SYS_ID2`) REFER ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_route
-- ----------------------------
DROP TABLE IF EXISTS `sb_route`;
CREATE TABLE `sb_route`  (
  `ID` int(11) NOT NULL  AUTO_INCREMENT,
  `SBR_ROUTENAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBR_START_STATION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBR_END_STATION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBR_DEPART_TIME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBR_DESC` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_route_station
-- ----------------------------
DROP TABLE IF EXISTS `sb_route_station`;
CREATE TABLE `sb_route_station`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int(11) NOT NULL,
  `SB__ID2` int(11) NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_8` USING BTREE(`SB__ID2`),
  INDEX `FK_Relationship_9` USING BTREE(`SB__ID`),
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`SB__ID`) REFERENCES `sb_station` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_8` FOREIGN KEY (`SB__ID2`) REFERENCES `sb_route` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`SB__ID2`) REFER `tt1/sb_route`(`ID`); (`SB__ID`) REFER ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_station
-- ----------------------------
DROP TABLE IF EXISTS `sb_station`;
CREATE TABLE `sb_station`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SBS_STATION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBS_LONGITUDE` float NULL DEFAULT NULL,
  `SBS_LATITUDE` float NULL DEFAULT NULL,
  `SBS_DEPART_TIME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SBS_DESC` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_user_bus
-- ----------------------------
DROP TABLE IF EXISTS `sb_user_bus`;
CREATE TABLE `sb_user_bus`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int(11) NOT NULL,
  `SYS_ID` int(11) NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_16` USING BTREE(`SYS_ID`),
  INDEX `FK_Relationship_17` USING BTREE(`SB__ID`),
  CONSTRAINT `FK_Relationship_17` FOREIGN KEY (`SB__ID`) REFERENCES `sb_route` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_16` FOREIGN KEY (`SYS_ID`) REFERENCES `sys_user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`SYS_ID`) REFER `tt1/sys_user`(`ID`); (`SB__ID`) REFER `' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sb_user_role`;
CREATE TABLE `sb_user_role`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYS_ID` int(11) NOT NULL COMMENT '外键，来自于系统角色表的id',
  `SYS_ID2` int(11) NOT NULL COMMENT '外键，来自于系统用户表的id',
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_15` USING BTREE(`SYS_ID2`),
  INDEX `FK_Relationship_18` USING BTREE(`SYS_ID`),
  CONSTRAINT `FK_Relationship_18` FOREIGN KEY (`SYS_ID`) REFERENCES `sys_role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Relationship_15` FOREIGN KEY (`SYS_ID2`) REFERENCES `sys_user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`SYS_ID2`) REFER `tt1/sys_user`(`ID`); (`SYS_ID`) REFER ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SA_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SA_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SA_DISPLAY_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限表,分组,细化到每个动作上' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设置自动自增_',
  `SL_USER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SL_DEPARTMENT` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SL_OPERATOR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SL_MODULE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SL_IP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SL_MAC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SL_CONTENT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设置自动递增_',
  `SP_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_VALUE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_DEFAULT_VALUE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用于存放系统参数' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设置自动递增_',
  `SR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SR_MANAGE` tinyint(1) NULL DEFAULT NULL COMMENT '0-否\r\n            1-是',
  `SR_LEVEL` int(11) NULL DEFAULT NULL COMMENT '0-管理员\r\n            1-经销商\r\n            2-个人',
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统拥有的各类角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `B_D_ID` int(11) NOT NULL,
  `LOGIN_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一、不为空、自动生成',
  `WORKID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REAL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SALT` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ID_CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PHONE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ACCOUNT_DATE` date NULL DEFAULT NULL,
  `LAST_PSD_DATE` date NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`),
  INDEX `FK_Relationship_19` USING BTREE(`B_D_ID`),
  UNIQUE INDEX `Index_LOGIN_NAME` USING BTREE(`LOGIN_NAME`),
  CONSTRAINT `FK_Relationship_19` FOREIGN KEY (`B_D_ID`) REFERENCES `b_department` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 10240 kB; (`B_D_ID`) REFER `tt1/b_department`(`ID`)' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
