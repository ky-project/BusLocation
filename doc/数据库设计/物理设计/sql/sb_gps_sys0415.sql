/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     19/04/15 23:52:58                            */
/*==============================================================*/


drop table if exists B_DEPARTMENT;

drop table if exists FILE_ATTACH;

drop table if exists P_NOTICE;

drop table if exists P_NOTICE_ATTACH;

drop table if exists SB_BUS;

drop table if exists SB_BUSPOSITION;

drop table if exists SB_BUSPOSITION_HIS;

drop table if exists SB_BUS_ROUTE;

drop table if exists SB_GPS;

drop table if exists SB_ROLE_AUTHORITY;

drop table if exists SB_ROUTE;

drop table if exists SB_ROUTE_STATION;

drop table if exists SB_STATION;

drop table if exists SB_USER_BUS;

drop table if exists SB_USER_ROLE;

drop table if exists SYS_AUTHORITY;

drop table if exists SYS_LOG;

drop table if exists SYS_PARAMETER;

drop table if exists SYS_ROLE;

drop table if exists SYS_USER;

/*==============================================================*/
/* Table: B_DEPARTMENT                                          */
/*==============================================================*/
create table B_DEPARTMENT
(
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: FILE_ATTACH                                           */
/*==============================================================*/
create table FILE_ATTACH
(
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: P_NOTICE                                              */
/*==============================================================*/
create table P_NOTICE
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SYS_ID` int(11) NOT NULL,
  `PN_TOPIC` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PN_CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `PN_TYPE` int(11) NULL DEFAULT NULL COMMENT '0-新闻 1-公告 2-供需发布  3-活动发布 4-法律服务 5-政策法规 6-资料下载',
  `PN_STATE` int(11) NULL DEFAULT 0 COMMENT '0-草稿 1-待审核 2-已发布',
  `PN_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

alter table P_NOTICE comment '0-新闻 1-公告 2-供需发布 3-活动发布 4-法律服务';

/*==============================================================*/
/* Table: P_NOTICE_ATTACH                                       */
/*==============================================================*/
create table P_NOTICE_ATTACH
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `P_N_ID` int(11) NOT NULL,
  `FIL_ID` int(11) NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true, 
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_BUS                                                */
/*==============================================================*/
create table SB_BUS
(
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);


/*==============================================================*/
/* Table: SB_BUSPOSITION                                        */
/*==============================================================*/
create table SB_BUSPOSITION
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int NOT NULL comment 'GPS信号中自带的识别码作为主键',
  `SBP_RECODE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SBP_LONGITUDE` double NULL DEFAULT NULL,
  `SBP_LATITUDE` double NULL DEFAULT NULL,
  `SBP_VELOCITY` double NULL DEFAULT NULL,
  `SBP_DIRECTION` double NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_BUSPOSITION_HIS                                    */
/*==============================================================*/
create table SB_BUSPOSITION_HIS
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int NOT NULL  comment 'GPS信号中自带的识别码作为主键',
  `SBP_RECODE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SBP_LONGITUDE` double NULL DEFAULT NULL,
  `SBP_LATITUDE` double NULL DEFAULT NULL,
  `SBP_VELOCITY` double NULL DEFAULT NULL,
  `SBP_DIRECTION` double NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_BUS_ROUTE                                          */
/*==============================================================*/
create table SB_BUS_ROUTE
(
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_GPS                                                */
/*==============================================================*/
create table SB_GPS
(
  `ID` int(11) NOT NULL COMMENT '设定自增_GPS信号中自带的识别码作为主键',
  `SB__ID` int(11) NOT NULL,
   SBG_NUMBER           varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   SBG_TYPE             varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   SBG_FACTORY_NAME     varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_ROLE_AUTHORITY                                     */
/*==============================================================*/
create table SB_ROLE_AUTHORITY
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SYS_ID` int(11) NOT NULL,
  `SYS_ID2` int(11) NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_ROUTE                                              */
/*==============================================================*/
create table SB_ROUTE
(
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_ROUTE_STATION                                      */
/*==============================================================*/
create table SB_ROUTE_STATION
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int(11) NOT NULL,
  `SB__ID2` int(11) NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_STATION                                            */
/*==============================================================*/
create table SB_STATION
(
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_USER_BUS                                           */
/*==============================================================*/
create table SB_USER_BUS
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SB__ID` int(11) NOT NULL,
  `SYS_ID` int(11) NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SB_USER_ROLE                                          */
/*==============================================================*/
create table SB_USER_ROLE
(
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYS_ID` int(11) NOT NULL COMMENT '外键，来自于系统角色表的id',
  `SYS_ID2` int(11) NOT NULL COMMENT '外键，来自于系统用户表的id',
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SYS_AUTHORITY                                         */
/*==============================================================*/
create table SYS_AUTHORITY
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `SA_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SA_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SA_DISPLAY_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

alter table SYS_AUTHORITY comment '系统权限表,分组,细化到每个动作上';

/*==============================================================*/
/* Table: SYS_LOG                                               */
/*==============================================================*/
create table SYS_LOG
(
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

/*==============================================================*/
/* Table: SYS_PARAMETER                                         */
/*==============================================================*/
create table SYS_PARAMETER
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设置自动递增_',
  `SP_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_VALUE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SP_DEFAULT_VALUE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

alter table SYS_PARAMETER comment '用于存放系统参数';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设置自动递增_',
  `SR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SR_MANAGE` tinyint(1) NULL DEFAULT NULL COMMENT '0-否 1-是',
  `SR_LEVEL` int(11) NULL DEFAULT NULL COMMENT '0-管理员 1-经销商 2-个人',
  `REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `REMARK3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

alter table SYS_ROLE comment '系统拥有的各类角色';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '设定自增_',
  `B_D_ID` int(11) NOT NULL,
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
  `UPDATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Valid`                bool NULL DEFAULT true,
   primary key (ID)
);

alter table P_NOTICE add constraint FK_Relationship_12 foreign key (SYS_ID)
      references SYS_USER (ID) on delete restrict on update restrict;

alter table P_NOTICE_ATTACH add constraint FK_Relationship_13 foreign key (P_N_ID)
      references P_NOTICE (ID) on delete restrict on update restrict;

alter table P_NOTICE_ATTACH add constraint FK_Relationship_14 foreign key (FIL_ID)
      references FILE_ATTACH (ID) on delete restrict on update restrict;

alter table SB_BUSPOSITION add constraint FK_Relationship_24 foreign key (SB__ID)
      references SB_GPS (ID) on delete restrict on update restrict;

alter table SB_BUSPOSITION_HIS add constraint FK_Relationship_25 foreign key (SB__ID)
      references SB_GPS (ID) on delete restrict on update restrict;

alter table SB_BUS_ROUTE add constraint FK_Relationship_6 foreign key (SB__ID)
      references SB_BUS (ID) on delete restrict on update restrict;

alter table SB_BUS_ROUTE add constraint FK_Relationship_7 foreign key (SB__ID2)
      references SB_ROUTE (ID) on delete restrict on update restrict;

alter table SB_GPS add constraint FK_Relationship_20 foreign key (SB__ID)
      references SB_BUS (ID) on delete restrict on update restrict;

alter table SB_ROLE_AUTHORITY add constraint FK_Relationship_21 foreign key (SYS_ID)
      references SYS_ROLE (ID) on delete restrict on update restrict;

alter table SB_ROLE_AUTHORITY add constraint FK_Relationship_22 foreign key (SYS_ID2)
      references SYS_AUTHORITY (ID) on delete restrict on update restrict;

alter table SB_ROUTE_STATION add constraint FK_Relationship_8 foreign key (SB__ID2)
      references SB_ROUTE (ID) on delete restrict on update restrict;

alter table SB_ROUTE_STATION add constraint FK_Relationship_9 foreign key (SB__ID)
      references SB_STATION (ID) on delete restrict on update restrict;

alter table SB_USER_BUS add constraint FK_Relationship_16 foreign key (SYS_ID)
      references SYS_USER (ID) on delete restrict on update restrict;

alter table SB_USER_BUS add constraint FK_Relationship_17 foreign key (SB__ID)
      references SB_ROUTE (ID) on delete restrict on update restrict;

alter table SB_USER_ROLE add constraint FK_Relationship_15 foreign key (SYS_ID2)
      references SYS_USER (ID) on delete restrict on update restrict;

alter table SB_USER_ROLE add constraint FK_Relationship_18 foreign key (SYS_ID)
      references SYS_ROLE (ID) on delete restrict on update restrict;

alter table SYS_USER add constraint FK_Relationship_19 foreign key (B_D_ID)
      references B_DEPARTMENT (ID) on delete restrict on update restrict;

