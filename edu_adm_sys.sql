/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0.19
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : edu_adm_sys

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 29/04/2020 16:24:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_class
-- ----------------------------
DROP TABLE IF EXISTS `tbl_class`;
CREATE TABLE `tbl_class`  (
  `class_no` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '班级编号 4位年级2位学院2位班级',
  `class_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '专业名称',
  `class_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属学院编号',
  PRIMARY KEY (`class_no`) USING BTREE,
  INDEX `fk_class_department`(`class_dpt`) USING BTREE,
  CONSTRAINT `fk_class_department` FOREIGN KEY (`class_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_class
-- ----------------------------
INSERT INTO `tbl_class` VALUES ('20170101', '文学一班', '01');
INSERT INTO `tbl_class` VALUES ('20171101', '计算机科学一班', '11');
INSERT INTO `tbl_class` VALUES ('20171102', '计算机科学二班', '11');
INSERT INTO `tbl_class` VALUES ('20171103', '网路工程', '11');
INSERT INTO `tbl_class` VALUES ('20181103', '网路工程', '11');

-- ----------------------------
-- Table structure for tbl_course
-- ----------------------------
DROP TABLE IF EXISTS `tbl_course`;
CREATE TABLE `tbl_course`  (
  `crs_no` char(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '2位学院3位编号',
  `crs_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程名称',
  `crs_cdt` tinyint(1) NOT NULL COMMENT '学分',
  `crs_hours` tinyint(1) NOT NULL COMMENT '学时',
  `crs_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '开设学院',
  `crs_type` enum('专业选修','专业必修','通识选修','通识必修') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程类型',
  PRIMARY KEY (`crs_no`) USING BTREE,
  INDEX `fk_course_department`(`crs_dpt`) USING BTREE,
  CONSTRAINT `fk_course_department` FOREIGN KEY (`crs_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_course
-- ----------------------------
INSERT INTO `tbl_course` VALUES ('11001', '数据结构', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES ('11002', '计算机组成原理', 4, 64, '11', '专业必修');
INSERT INTO `tbl_course` VALUES ('11003', '算法设计', 3, 54, '11', '专业选修');
INSERT INTO `tbl_course` VALUES ('11004', '网络编程', 3, 54, '11', '专业选修');
INSERT INTO `tbl_course` VALUES ('11005', '形式与政策', 1, 32, '11', '通识必修');
INSERT INTO `tbl_course` VALUES ('11006', '编程艺术', 1, 32, '11', '通识选修');

-- ----------------------------
-- Table structure for tbl_department
-- ----------------------------
DROP TABLE IF EXISTS `tbl_department`;
CREATE TABLE `tbl_department`  (
  `dpt_no` char(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院编号',
  `dpt_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`dpt_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_department
-- ----------------------------
INSERT INTO `tbl_department` VALUES ('01', '文学院');
INSERT INTO `tbl_department` VALUES ('02', '经管学院');
INSERT INTO `tbl_department` VALUES ('03', '外国语学院');
INSERT INTO `tbl_department` VALUES ('04', '物理与电子学院');
INSERT INTO `tbl_department` VALUES ('05', '化学与材料学院');
INSERT INTO `tbl_department` VALUES ('06', '数学学院');
INSERT INTO `tbl_department` VALUES ('07', '生命科学学院');
INSERT INTO `tbl_department` VALUES ('08', '体育学院');
INSERT INTO `tbl_department` VALUES ('09', '音乐学院');
INSERT INTO `tbl_department` VALUES ('10', '美术学院');
INSERT INTO `tbl_department` VALUES ('11', '计算机科学学院');

-- ----------------------------
-- Table structure for tbl_score
-- ----------------------------
DROP TABLE IF EXISTS `tbl_score`;
CREATE TABLE `tbl_score`  (
  `score_no` int(0) NOT NULL AUTO_INCREMENT COMMENT '标识一次成绩的唯一标识',
  `stu_no` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学生学号',
  `tea_crs_no` int(0) NULL DEFAULT NULL COMMENT '确定一次行课的唯一编号',
  `score_pro` int(0) NULL DEFAULT NULL COMMENT '平时成绩',
  `score_end` int(0) NULL DEFAULT NULL COMMENT '期末成绩',
  `score` int(0) NULL DEFAULT NULL COMMENT '综合成绩',
  PRIMARY KEY (`score_no`) USING BTREE,
  INDEX `fk_score_student`(`stu_no`) USING BTREE,
  INDEX `fk_score_tea_crs`(`tea_crs_no`) USING BTREE,
  CONSTRAINT `fk_score_student` FOREIGN KEY (`stu_no`) REFERENCES `tbl_student` (`stu_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_score_tea_crs` FOREIGN KEY (`tea_crs_no`) REFERENCES `tbl_tea_crs` (`tea_crs_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_score
-- ----------------------------
INSERT INTO `tbl_score` VALUES (1, '2017110323', 1, NULL, NULL, 80);

-- ----------------------------
-- Table structure for tbl_spot
-- ----------------------------
DROP TABLE IF EXISTS `tbl_spot`;
CREATE TABLE `tbl_spot`  (
  `spt_no` char(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '2位区域2位教室',
  `spt_area` enum('教学楼A区','教学楼B区','第一实验楼','第一操场') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '区域',
  `spt_room` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教室',
  PRIMARY KEY (`spt_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_spot
-- ----------------------------
INSERT INTO `tbl_spot` VALUES ('0101', '教学楼A区', 'A101');
INSERT INTO `tbl_spot` VALUES ('0102', '教学楼A区', 'A102');
INSERT INTO `tbl_spot` VALUES ('0103', '教学楼A区', 'A103');
INSERT INTO `tbl_spot` VALUES ('0104', '教学楼A区', 'A201');
INSERT INTO `tbl_spot` VALUES ('0105', '教学楼A区', 'A202');
INSERT INTO `tbl_spot` VALUES ('0106', '教学楼A区', 'A103');
INSERT INTO `tbl_spot` VALUES ('0201', '教学楼B区', 'B101');
INSERT INTO `tbl_spot` VALUES ('0202', '教学楼B区', 'B102');
INSERT INTO `tbl_spot` VALUES ('0301', '第一实验楼', '401');
INSERT INTO `tbl_spot` VALUES ('0302', '第一实验楼', '402');
INSERT INTO `tbl_spot` VALUES ('0303', '第一实验楼', '501');

-- ----------------------------
-- Table structure for tbl_student
-- ----------------------------
DROP TABLE IF EXISTS `tbl_student`;
CREATE TABLE `tbl_student`  (
  `stu_no` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学号前4位入学年份2位学院2位班级2位编号',
  `stu_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生姓名',
  `stu_sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别男、女',
  `stu_gdu` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '毕业学校',
  `stu_bth` date NOT NULL COMMENT '出生日期',
  `stu_id` char(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `stu_tel` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `stu_pol` enum('共青团员','共产党员','入党积极分子','其他党派','群众') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '群众' COMMENT '政治面貌',
  `stu_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属学院',
  `stu_class` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '4位年级2位学院2位班级',
  `stu_reg` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '籍贯',
  `stu_sta` enum('毕业','在校','休学','退学') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '在校' COMMENT '状态',
  `stu_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`stu_no`) USING BTREE,
  INDEX `fk_student_department`(`stu_dpt`) USING BTREE,
  INDEX `fk_student_class`(`stu_class`) USING BTREE,
  CONSTRAINT `fk_student_department` FOREIGN KEY (`stu_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_student_class` FOREIGN KEY (`stu_class`) REFERENCES `tbl_class` (`class_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_student
-- ----------------------------
INSERT INTO `tbl_student` VALUES ('2016110312', '候同学', '男', '第三中学', '1998-12-14', '510722199812147564', '18011108917', '共青团员', '11', '20171103', '四川成都', '在校', '123456');
INSERT INTO `tbl_student` VALUES ('2017110301', '刘同学', '女', '第五中学', '1998-12-14', '510722199812147561', '18011108917', '共青团员', '11', '20171103', '四川成都', '在校', '123456');
INSERT INTO `tbl_student` VALUES ('2017110302', '赵同学', '女', '第六中学', '1998-12-14', '51072219981214146X', '18011108917', '共青团员', '11', '20171103', '四川成都', '在校', '123456');
INSERT INTO `tbl_student` VALUES ('2017110305', '李同学', '女', '实验中学', '1998-12-14', '510722199812111111', '18011108917', '共青团员', '11', '20171103', '四川成都', '在校', '123456');
INSERT INTO `tbl_student` VALUES ('2017110314', '黄同学', '男', '第二中学', '1998-12-14', '510722199812149632', '18011122222', '共青团员', '11', '20171103', '四川成都', '在校', '123456');
INSERT INTO `tbl_student` VALUES ('2017110318', '雷同学', '女', '第一中学', '1998-12-14', '510722199812147894', '19982031936', '共青团员', '11', '20171103', '四川成都', '在校', '123456');
INSERT INTO `tbl_student` VALUES ('2017110323', '李同学', '女', '实验中学', '1998-12-14', '510722199812142384', '18011108917', '共青团员', '11', '20171103', '四川成都', '在校', '123456');
INSERT INTO `tbl_student` VALUES ('2017110401', '张同学', '男', '第四中学', '1998-12-14', '510722199812141234', '18011108917', '共青团员', '11', '20171103', '四川成都', '在校', '123456');

-- ----------------------------
-- Table structure for tbl_tea_crs
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tea_crs`;
CREATE TABLE `tbl_tea_crs`  (
  `tea_crs_no` int(0) NOT NULL AUTO_INCREMENT COMMENT '某次授课的唯一编号',
  `tea_no` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '教师编号',
  `crs_no` char(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程编号',
  `term` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上课学年',
  `time` tinyint(0) NULL DEFAULT NULL COMMENT '上课时间',
  `spot` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上课地点',
  PRIMARY KEY (`tea_crs_no`) USING BTREE,
  INDEX `fk_tea_crs_course`(`crs_no`) USING BTREE,
  INDEX `fk_tea_crs_term`(`term`) USING BTREE,
  INDEX `fk_tea_crs_spot`(`spot`) USING BTREE,
  INDEX `fk_tea_crs_teacher`(`tea_no`) USING BTREE,
  CONSTRAINT `fk_tea_crs_term` FOREIGN KEY (`term`) REFERENCES `tbl_term` (`term_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_tea_crs_spot` FOREIGN KEY (`spot`) REFERENCES `tbl_spot` (`spt_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_tea_crs_course` FOREIGN KEY (`crs_no`) REFERENCES `tbl_course` (`crs_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_tea_crs_teacher` FOREIGN KEY (`tea_no`) REFERENCES `tbl_teacher` (`tea_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_tea_crs
-- ----------------------------
INSERT INTO `tbl_tea_crs` VALUES (1, '20011103', '11001', '20191', 1, '0102');
INSERT INTO `tbl_tea_crs` VALUES (2, '20051106', '11002', '20191', 2, '0103');

-- ----------------------------
-- Table structure for tbl_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tbl_teacher`;
CREATE TABLE `tbl_teacher`  (
  `tea_no` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '工号4位入职年份2位学院2位编号',
  `tea_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `tea_sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `tea_acd` enum('本科','硕士','博士','其他') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学历',
  `tea_title` enum('教授','副教授','讲师','助教') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职称',
  `tea_birth` date NOT NULL COMMENT '出生日期',
  `tea_id` char(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `tea_tel` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `tea_pol` enum('共产党员','共青团员','其他党派','入党积极分子','群众') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '群众' COMMENT '政治面貌',
  `rea_dpt` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属学院',
  `tea_reg` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '籍贯',
  `tea_wkt` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '离职时间',
  `tea_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`tea_no`) USING BTREE,
  INDEX `fk_teacher_department`(`rea_dpt`) USING BTREE,
  CONSTRAINT `fk_teacher_department` FOREIGN KEY (`rea_dpt`) REFERENCES `tbl_department` (`dpt_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_teacher
-- ----------------------------
INSERT INTO `tbl_teacher` VALUES ('20011103', '李老师', '女', '博士', '教授', '1976-04-14', '510722197604147894', '13811104563', '共产党员', '11', '四川成都', '2001-09至今', '123456');
INSERT INTO `tbl_teacher` VALUES ('20051106', '刘老师', '男', '硕士', '助教', '1976-04-14', '510722197604147894', '13511163563', '共产党员', '11', '四川成都', '2005-09至今', '123456');
INSERT INTO `tbl_teacher` VALUES ('20061101', '张老师', '男', '硕士', '讲师', '1976-04-14', '510722197604147894', '15811104563', '群众', '11', '四川成都', '2006-09至今', '123456');
INSERT INTO `tbl_teacher` VALUES ('20061102', '张老师', '女', '硕士', '讲师', '1976-03-14', '510722197603147894', '15814504563', '群众', '11', '四川成都', '2006-09至今', '123456');
INSERT INTO `tbl_teacher` VALUES ('20061104', '王老师', '男', '博士', '副教授', '1976-04-14', '510722197604147894', '15811185563', '共产党员', '11', '四川成都', '2006-09至今', '123456');
INSERT INTO `tbl_teacher` VALUES ('20061105', '赵老师', '女', '硕士', '讲师', '1976-04-14', '510722197604147894', '18717894563', '共产党员', '11', '四川成都', '2006-09至今', '123456');
INSERT INTO `tbl_teacher` VALUES ('20151107', '周老师', '男', '本科', '讲师', '1976-04-14', '510722197604147894', '18011244563', '共产党员', '11', '四川成都', '2015-09至今', '123456');

-- ----------------------------
-- Table structure for tbl_term
-- ----------------------------
DROP TABLE IF EXISTS `tbl_term`;
CREATE TABLE `tbl_term`  (
  `term_no` char(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '4位年份1位学期 1表示春季 2表示秋季',
  `term_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`term_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_term
-- ----------------------------
INSERT INTO `tbl_term` VALUES ('20152', '2015-2016第一学年');
INSERT INTO `tbl_term` VALUES ('20161', '2015-2016第二学年');
INSERT INTO `tbl_term` VALUES ('20162', '2016-2017第一学年');
INSERT INTO `tbl_term` VALUES ('20171', '2016-2017第二学年');
INSERT INTO `tbl_term` VALUES ('20172', '2017-2018第一学年');
INSERT INTO `tbl_term` VALUES ('20181', '2017-2018第二学年');
INSERT INTO `tbl_term` VALUES ('20182', '2018-2019第一学年');
INSERT INTO `tbl_term` VALUES ('20191', '2018-2019第二学年');
INSERT INTO `tbl_term` VALUES ('20192', '2019-2020第一学年');
INSERT INTO `tbl_term` VALUES ('20201', '2019-2020第二学年');

SET FOREIGN_KEY_CHECKS = 1;
