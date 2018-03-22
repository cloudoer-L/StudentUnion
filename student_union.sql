/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : student_union

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-22 21:49:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for su_classes
-- ----------------------------
DROP TABLE IF EXISTS `su_classes`;
CREATE TABLE `su_classes` (
  `id` varchar(255) NOT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3FB56B49F9311B19` (`teacherId`),
  CONSTRAINT `FK3FB56B49F9311B19` FOREIGN KEY (`teacherId`) REFERENCES `su_teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_classes
-- ----------------------------
INSERT INTO `su_classes` VALUES ('402890ed62489c65016248d14d070000', '2017', '一班', '00332', '0', '402881e4618e2fb501618e3077300000');

-- ----------------------------
-- Table structure for su_classes_dormitory
-- ----------------------------
DROP TABLE IF EXISTS `su_classes_dormitory`;
CREATE TABLE `su_classes_dormitory` (
  `dormitoryId` varchar(255) NOT NULL,
  `classesId` varchar(255) NOT NULL,
  PRIMARY KEY (`classesId`,`dormitoryId`),
  KEY `FKD8861C2F9C65F2A1` (`classesId`),
  KEY `FKD8861C2F428E535F` (`dormitoryId`),
  CONSTRAINT `FKD8861C2F428E535F` FOREIGN KEY (`dormitoryId`) REFERENCES `su_dormitory` (`id`),
  CONSTRAINT `FKD8861C2F9C65F2A1` FOREIGN KEY (`classesId`) REFERENCES `su_classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_classes_dormitory
-- ----------------------------

-- ----------------------------
-- Table structure for su_department
-- ----------------------------
DROP TABLE IF EXISTS `su_department`;
CREATE TABLE `su_department` (
  `id` varchar(255) NOT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `leaderId` varchar(255) DEFAULT NULL,
  `superiorId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `leaderId` (`leaderId`),
  KEY `FK1C79194FD18201E4` (`superiorId`),
  KEY `FK1C79194F5A081884` (`leaderId`),
  CONSTRAINT `FK1C79194F5A081884` FOREIGN KEY (`leaderId`) REFERENCES `su_member` (`id`),
  CONSTRAINT `FK1C79194FD18201E4` FOREIGN KEY (`superiorId`) REFERENCES `su_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_department
-- ----------------------------

-- ----------------------------
-- Table structure for su_dormitory
-- ----------------------------
DROP TABLE IF EXISTS `su_dormitory`;
CREATE TABLE `su_dormitory` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `adminId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `adminId` (`adminId`),
  KEY `FK6C767408B621E19F` (`adminId`),
  CONSTRAINT `FK6C767408B621E19F` FOREIGN KEY (`adminId`) REFERENCES `su_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_dormitory
-- ----------------------------

-- ----------------------------
-- Table structure for su_member
-- ----------------------------
DROP TABLE IF EXISTS `su_member`;
CREATE TABLE `su_member` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `departmentId` varchar(255) DEFAULT NULL,
  `personId` varchar(255) DEFAULT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `personId` (`personId`),
  UNIQUE KEY `roleId` (`roleId`),
  KEY `FK75D9E717AF01EC0D` (`roleId`),
  KEY `FK75D9E717FF6AEE45` (`departmentId`),
  KEY `FK75D9E7171C881C8B` (`personId`),
  CONSTRAINT `FK75D9E7171C881C8B` FOREIGN KEY (`personId`) REFERENCES `su_person` (`id`),
  CONSTRAINT `FK75D9E717AF01EC0D` FOREIGN KEY (`roleId`) REFERENCES `su_role` (`id`),
  CONSTRAINT `FK75D9E717FF6AEE45` FOREIGN KEY (`departmentId`) REFERENCES `su_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_member
-- ----------------------------

-- ----------------------------
-- Table structure for su_menu
-- ----------------------------
DROP TABLE IF EXISTS `su_menu`;
CREATE TABLE `su_menu` (
  `id` varchar(255) NOT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `ico` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `superiorId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK90816D1C804A1071` (`superiorId`),
  CONSTRAINT `FK90816D1C804A1071` FOREIGN KEY (`superiorId`) REFERENCES `su_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_menu
-- ----------------------------
INSERT INTO `su_menu` VALUES ('402890ed621a692301621a6a7eaf0000', '2', '', '', '教师管理', '020010000', '', 'teacher/indexUI', 'basics_teacher', '402890ee621563fe016215659ace0000');
INSERT INTO `su_menu` VALUES ('402890ed6224b276016224b4c9070000', '3', '', '', '添加一级菜单', '010010010', '', 'menu/addUI', 'menu_index_add', 'ff80808161ef88380161ef8841680001');
INSERT INTO `su_menu` VALUES ('402890ed6224b276016224b5d0480001', '3', '', '', '添加下级菜单', '010010020', '', 'menu/addUI', 'menu_index_addNext', 'ff80808161ef88380161ef8841680001');
INSERT INTO `su_menu` VALUES ('402890ed6224b276016224b6a87a0002', '3', '', '', '修改菜单', '010010030', '', 'menu/addUI', 'menu_index_update', 'ff80808161ef88380161ef8841680001');
INSERT INTO `su_menu` VALUES ('402890ed6224b276016224b750fe0003', '3', '', '', '删除菜单', '010010040', '', 'menu/delete', 'menu_index_delete', 'ff80808161ef88380161ef8841680001');
INSERT INTO `su_menu` VALUES ('402890ed6224b276016224b83b690004', '3', '', '', '添加教师', '020010010', '', 'teacher/addUI', 'teacher_index_add', '402890ed621a692301621a6a7eaf0000');
INSERT INTO `su_menu` VALUES ('402890ed6224b276016224b911390005', '3', '', '', '修改教师', '020010020', '', 'teacher/addUI', 'teacher_index_update', '402890ed621a692301621a6a7eaf0000');
INSERT INTO `su_menu` VALUES ('402890ed6224b276016224b9b1900006', '3', '', '', '删除教师', '020010030', 'open', 'teacher/delete', 'teacher_index_delete', '402890ed621a692301621a6a7eaf0000');
INSERT INTO `su_menu` VALUES ('402890ed6243661e0162436e926f0000', '3', '', '', '批量导入', '020010040', '', 'teacher/import', 'teacher_index_import', '402890ed621a692301621a6a7eaf0000');
INSERT INTO `su_menu` VALUES ('402890ed6243661e0162436f933a0001', '3', '', '', '批量导出', '020010050', '', 'teacher/exportFile', 'teacher_index_export', '402890ed621a692301621a6a7eaf0000');
INSERT INTO `su_menu` VALUES ('402890ed6243661e0162437069b90002', '2', '', '', '班级管理', '020020000', 'open', 'classes/indexUI', 'basics_classes', '402890ee621563fe016215659ace0000');
INSERT INTO `su_menu` VALUES ('402890ed624dca2801624dcd31570000', '2', '', '', '班级职务管理', '020080000', '', 'position/indexUI', 'basics_duty', '402890ee621563fe016215659ace0000');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624dee05740000', '3', '', '', '添加班级', '020020010', '', 'classes/addUI', 'classes_index_add', '402890ed6243661e0162437069b90002');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624deeb7ea0001', '3', '', '', '修改班级', '020020020', '', 'classes/addUI', 'classes_index_update', '402890ed6243661e0162437069b90002');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df01cf50002', '3', '', '', '删除班级', '020020030', '', 'classes/delete', 'classes_index_delete', '402890ed6243661e0162437069b90002');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df0c9820003', '3', '', '', '批量导入', '020020040', '', 'classes/importUI', 'classes_index_import', '402890ed6243661e0162437069b90002');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df177130004', '3', '', '', '批量导出', '020020050', 'open', 'classes/exportFile', 'classes_index_export', '402890ed6243661e0162437069b90002');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df26ace0005', '3', '', '', '添加职务', '020080010', '', 'position/addUI', 'position_index_add', '402890ed624dca2801624dcd31570000');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df2ef940006', '3', '', '', '修改职务', '020080020', '', 'position/addUI', 'position_index_update', '402890ed624dca2801624dcd31570000');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df38bc00007', '3', '', '', '删除职务', '020080030', '', 'position/delete', 'position_index_delete', '402890ed624dca2801624dcd31570000');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df4543f0008', '3', '', '', '批量导入', '020080040', '', 'position/importUI', 'position_index_import', '402890ed624dca2801624dcd31570000');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df50d0b0009', '3', '', '', '批量导出', '020080050', '', 'position/exportFile', 'position_index_export', '402890ed624dca2801624dcd31570000');
INSERT INTO `su_menu` VALUES ('402890ed624deca201624df76c76000a', '2', '', '', '寝室管理', '020030000', '', '', 'basics/dormitory', '402890ee621563fe016215659ace0000');
INSERT INTO `su_menu` VALUES ('402890ee621563fe016215659ace0000', '1', '', '', '基础数据', '020000000', '', '', 'basics', null);
INSERT INTO `su_menu` VALUES ('ff80808161ef88380161ef8841450000', '1', null, null, '开发人员选项', '010000000', null, null, 'developer', null);
INSERT INTO `su_menu` VALUES ('ff80808161ef88380161ef8841680001', '2', 'icon-search', null, '菜单管理', '010010000', null, 'menu/indexUI', 'developer_menu', 'ff80808161ef88380161ef8841450000');

-- ----------------------------
-- Table structure for su_person
-- ----------------------------
DROP TABLE IF EXISTS `su_person`;
CREATE TABLE `su_person` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idCard` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_person
-- ----------------------------
INSERT INTO `su_person` VALUES ('402881e4618e2fb501618e3077300000', '', '', '教师', '02000', '8687', '87', '男');
INSERT INTO `su_person` VALUES ('402890ed624386950162438cbc740000', 'a', 'a', 'a', 'a', 'a', 'a', 'a');

-- ----------------------------
-- Table structure for su_position
-- ----------------------------
DROP TABLE IF EXISTS `su_position`;
CREATE TABLE `su_position` (
  `id` varchar(255) NOT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `positionId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBA44CA664C6068F9` (`positionId`),
  CONSTRAINT `FKBA44CA664C6068F9` FOREIGN KEY (`positionId`) REFERENCES `su_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_position
-- ----------------------------
INSERT INTO `su_position` VALUES ('402890ed624dca2801624dd4438f0001', '1', '班长', '101', '', null);

-- ----------------------------
-- Table structure for su_role
-- ----------------------------
DROP TABLE IF EXISTS `su_role`;
CREATE TABLE `su_role` (
  `id` varchar(255) NOT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_role
-- ----------------------------

-- ----------------------------
-- Table structure for su_student
-- ----------------------------
DROP TABLE IF EXISTS `su_student`;
CREATE TABLE `su_student` (
  `state` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `classesId` varchar(255) DEFAULT NULL,
  `dormitoryId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9CD2B55E9C65F2A1` (`classesId`),
  KEY `FK9CD2B55E428E535F` (`dormitoryId`),
  KEY `FK9CD2B55E21E0276` (`id`),
  CONSTRAINT `FK9CD2B55E21E0276` FOREIGN KEY (`id`) REFERENCES `su_person` (`id`),
  CONSTRAINT `FK9CD2B55E428E535F` FOREIGN KEY (`dormitoryId`) REFERENCES `su_dormitory` (`id`),
  CONSTRAINT `FK9CD2B55E9C65F2A1` FOREIGN KEY (`classesId`) REFERENCES `su_classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_student
-- ----------------------------

-- ----------------------------
-- Table structure for su_teacher
-- ----------------------------
DROP TABLE IF EXISTS `su_teacher`;
CREATE TABLE `su_teacher` (
  `state` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB705FF0521E0276` (`id`),
  CONSTRAINT `FKB705FF0521E0276` FOREIGN KEY (`id`) REFERENCES `su_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_teacher
-- ----------------------------
INSERT INTO `su_teacher` VALUES ('', '402881e4618e2fb501618e3077300000');
INSERT INTO `su_teacher` VALUES ('a', '402890ed624386950162438cbc740000');

-- ----------------------------
-- Table structure for su_user
-- ----------------------------
DROP TABLE IF EXISTS `su_user`;
CREATE TABLE `su_user` (
  `id` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `memberId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `memberId` (`memberId`),
  KEY `FK90854388D6473DD5` (`memberId`),
  CONSTRAINT `FK90854388D6473DD5` FOREIGN KEY (`memberId`) REFERENCES `su_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of su_user
-- ----------------------------
INSERT INTO `su_user` VALUES ('402881e661a28d460161a28d4fde0000', '123456', null, 'admin', null);
